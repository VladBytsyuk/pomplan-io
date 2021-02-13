package io.pomplan.common.elm


sealed class Effect : Elm.Effect {
    data class IllegalState(
        val state: Elm.State,
        val action: Elm.Action
    ) : Effect()

    sealed class Timer : Effect() {
        object Run : Timer()
        object Stop : Timer()
    }
}
