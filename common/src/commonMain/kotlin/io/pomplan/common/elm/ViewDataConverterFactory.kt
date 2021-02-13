package io.pomplan.common.elm

import kotlin.reflect.KClass

class ViewDataConverterFactory : Elm.ViewDataConvertersFactory<State> {
    override fun <VD : Elm.ViewData> get(`class`: KClass<VD>): Elm.ViewDataConverter<State, VD> = when (`class`) {
        State::class -> object : Elm.ViewDataConverter<State, State> {
            override fun convert(state: State): State = state
        }
        else ->
            throw IllegalArgumentException("ViewData $`class` doe not registered in ${this::class.simpleName}")
    } as Elm.ViewDataConverter<State, VD>
}
