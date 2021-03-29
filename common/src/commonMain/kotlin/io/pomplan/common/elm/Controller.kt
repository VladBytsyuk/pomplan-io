package io.pomplan.common.elm

import io.pomplan.common.Logger
import io.pomplan.common.Timer


class Controller(
    logger: Logger,
    val timer: Timer
) : Elm.Controller<State, Action, Effect> by Elm.ControllerImpl(
    initialState = State.initial,
    initialAction = Action.Initial,
    effectHandler = EffectHandler(logger = logger, timer = timer,),
    reducer = Reducer(),
    converterFactory = ViewDataConverterFactory(),
    logger = logger
)
