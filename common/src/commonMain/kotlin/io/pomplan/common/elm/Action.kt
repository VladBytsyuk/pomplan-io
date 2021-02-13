package io.pomplan.common.elm


sealed class Action : Elm.Action {
    object Initial : Action()

    sealed class UserClick : Action() {
        sealed class Button : UserClick() {
            object Stop : Button()
            object Play : Button()
            object Pause : Button()
            object Skip : Button()
        }
    }

    object TimerTick : Action()
}
