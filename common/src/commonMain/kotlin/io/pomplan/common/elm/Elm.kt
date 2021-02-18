package io.pomplan.common.elm

import io.pomplan.common.Logger
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlin.reflect.KClass


interface Elm {
    interface State
    interface Action
    interface Effect
    interface ViewData

    interface EffectHandler<E : Effect, A : Action> {
        suspend fun handle(effect: E): A?
    }

    interface Reducer<S : State, A : Action, E : Effect> {
        fun reduce(state: S, action: A): Pair<S, E?> =
            if (!allowedCondition(state, action)) state to illegalStateEffect(state, action)
            else reduceValid(state, action)
        fun allowedCondition(state: S, action: A): Boolean = true
        fun illegalStateEffect(state: S, action: A): E
        fun reduceValid(state: S, action: A): Pair<S, E?>
    }

    interface ViewDataConverter<S : State, VD : ViewData> {
        fun convert(state: S): VD
    }

    interface ViewDataConvertersFactory<S: State> {
        fun <VD : ViewData> get(`class`: KClass<VD>): ViewDataConverter<S, VD>
    }

    interface Controller<S : State, A : Action, E : Effect> {
        val effectHandler: EffectHandler<E, A>
        val reducer: Reducer<S, A, E>
        val converterFactory: ViewDataConvertersFactory<S>

        val currentState: S
        fun setAction(action: A)

        fun <VD : ViewData> subscribeOnState(subscriber: String, vdClass: KClass<VD>, callback: suspend (VD) -> Unit)
        fun unsubscribeFromState(subscriber: String)

        fun attach()
        fun detach()

        fun lockStateSubscription(subscriber: String, reason: String)
        fun unlockStateSubscription(subscriber: String, reason: String)
    }


    class ControllerImpl<S : State, A : Action, E : Effect>(
        initialState: S,
        initialAction: A,
        override val effectHandler: EffectHandler<E, A>,
        override val reducer: Reducer<S, A, E>,
        override val converterFactory: ViewDataConvertersFactory<S>,
        private val logger: Logger
    ) : Controller<S, A, E> {
        private val actionFlow = MutableStateFlow(initialAction)
        private val stateFlow = MutableStateFlow(initialState)

        override val currentState: S get() = stateFlow.value

        override fun setAction(action: A) {
            actionScope.launch { actionFlow.emit(action) }
        }

        private val stateScope: CoroutineScope = CoroutineScope(Dispatchers.Default)
        private val subscribers: MutableMap<Any, Job> = hashMapOf()
        override fun <VD : ViewData> subscribeOnState(
            subscriber: String,
            vdClass: KClass<VD>,
            callback: suspend (VD) -> Unit
        ) {
            val converter = converterFactory.get(vdClass)
            val job = stateScope.launch {
                stateFlow.collect { newState ->
                    ensureActive()
                    if (lockMap[subscriber] != true) {
                        val viewModel = converter.convert(newState)
                        callback(viewModel)
                    }
                }
            }
            subscribers[subscriber] = job
        }

        override fun unsubscribeFromState(subscriber: String) {
            subscribers[subscriber]?.cancel()
            subscribers.remove(subscriber)
        }

        private var actionScope: CoroutineScope = createNewActionScope()
        override fun attach() {
            actionScope.launch {
                actionFlow.collect { newAction ->
                    ensureActive()
                    onActionReceived(newAction)
                }
            }
        }

        override fun detach() {
            actionScope.cancel()
            actionScope = createNewActionScope()
        }

        private fun createNewActionScope() = CoroutineScope(Dispatchers.Default)


        private suspend fun onActionReceived(action: A) {
            logger.debug(tag = "Elm-Action", message = action.toString())
            val oldState = stateFlow.value
            val (newState, effect) = reducer.reduce(oldState, action)
            if (newState != oldState) {
                logger.debug(tag = "Elm-State", message = newState.toString())
                stateFlow.emit(newState)
            }
            if (effect != null) actionScope.launch {
                logger.debug(tag = "Elm-Effect", message = effect.toString())
                val newAction = effectHandler.handle(effect)
                if (newAction != null) actionFlow.emit(newAction)
            }
        }


        private var lockMap: MutableMap<String, Boolean> = hashMapOf()
        override fun lockStateSubscription(subscriber: String, reason: String) {
            logger.info(tag = "Elm-Lock", message = "[$subscriber] Lock: $reason")
            lockMap[subscriber] = true
        }

        override fun unlockStateSubscription(subscriber: String, reason: String) {
            logger.info(tag = "Elm-Lock", message = "[$subscriber] Unlock: $reason")
            lockMap[subscriber] = false
        }
    }
}
