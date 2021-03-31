package io.pomplan.common.elm

import io.pomplan.common.domain.Time


sealed class Action : Elm.Action {
    object Initial : Action()

    sealed class UserClick : Action() {
        sealed class Button : UserClick() {
            object Stop : Button()
            object Play : Button()
            object Pause : Button()
            class Skip : Button()
        }
    }

    class TimerTick : Action()

    sealed class Settings : Action() {
        sealed class Change : Settings() {
            sealed class Time : Change() {
                data class Work(val newTime: io.pomplan.common.domain.Time) : Time()
                data class ShortBreak(val newTime: io.pomplan.common.domain.Time) : Time()
                data class LongBreak(val newTime: io.pomplan.common.domain.Time) : Time()
            }
            data class GroupSize(val newGroupSize: Int) : Change()
        }
    }
}
