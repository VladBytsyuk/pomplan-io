package io.pomplan.common.elm

import io.pomplan.common.domain.Pomodoro
import io.pomplan.common.domain.Settings
import io.pomplan.common.domain.Time


data class State(
    val mode: Mode,
    val pomodoro: Pomodoro,
    val settings: Settings,
) : Elm.State, Elm.ViewData {
    enum class Mode { TIMER, SETTINGS }

    companion object {
        val initial: State get() = State(
            mode = Mode.TIMER,
            pomodoro = Pomodoro(
                mode = Pomodoro.Mode.PRE_WORK,
                goalTime = Time(minute = 25),
                elapsedTime = Time(minute = 25),
                lastDoneNumber = 0,
            ),
            settings = Settings(
                workTime = Time(minute = 25),
                shortBreakTime = Time(minute = 5),
                longBreakTime = Time(minute = 15),
                groupSize = 4,
            )
        )
    }
}
