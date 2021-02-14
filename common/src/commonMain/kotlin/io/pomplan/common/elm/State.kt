package io.pomplan.common.elm

import io.pomplan.common.domain.Pomodoro
import io.pomplan.common.domain.workTime


data class State(
    val pomodoro: Pomodoro,
) : Elm.State, Elm.ViewData {
    companion object {
        val initial: State get() = State(
            pomodoro = Pomodoro(
                mode = Pomodoro.Mode.PRE_WORK,
                goalTime = workTime,
                elapsedTime = workTime,
                lastDoneNumber = 0,
            )
        )
    }
}
