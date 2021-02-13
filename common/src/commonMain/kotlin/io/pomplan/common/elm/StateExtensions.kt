package io.pomplan.common.elm

import io.pomplan.common.domain.Pomodoro
import io.pomplan.common.domain.Time
import io.pomplan.common.domain.Pomodoro.Mode.*
import io.pomplan.common.domain.second


fun State.stopPomodoro(): State = when (pomodoro.mode) {
    PRE_WORK, PRE_BREAK -> this
    WORK -> copy(
        pomodoro = pomodoro.copy(mode = PRE_WORK, elapsedTime = pomodoro.goalTime)
    )
    BREAK -> copy(
        pomodoro = pomodoro.copy(mode = PRE_BREAK, elapsedTime = pomodoro.goalTime)
    )
}

fun State.skipPomodoro(): State = when (pomodoro.mode) {
    WORK, PRE_WORK -> {
        val currentPomodoro = pomodoro.currentPomodoro + 1
        val goalTime = if (currentPomodoro % 4 == 0) Time(minute = 15) else Time(minute = 5)
        copy(
            pomodoro = pomodoro.copy(
                mode = PRE_BREAK,
                goalTime = goalTime,
                elapsedTime = goalTime,
                currentPomodoro = currentPomodoro
            )
        )
    }
    BREAK, PRE_BREAK -> {
        val goalTime = Time(minute = 25)
        copy(
            pomodoro = pomodoro.copy(
                mode = PRE_WORK,
                goalTime = goalTime,
                elapsedTime = goalTime
            )
        )
    }
}

fun State.playPomodoro(): State = when (pomodoro.mode) {
    WORK, BREAK -> this
    PRE_WORK -> changePomodoroMode(WORK)
    PRE_BREAK -> changePomodoroMode(BREAK)
}

fun State.pausePomodoro(): State = when (pomodoro.mode) {
    PRE_WORK, PRE_BREAK -> this
    WORK -> changePomodoroMode(PRE_WORK)
    BREAK -> changePomodoroMode(PRE_BREAK)
}


fun State.changePomodoroMode(mode: Pomodoro.Mode): State = copy(
    pomodoro = pomodoro.copy(mode = mode)
)

fun State.addSecondToElapsedTime(): State = copy(
    pomodoro = pomodoro.copy(
        elapsedTime = Time(milliseconds = pomodoro.elapsedTime.milliseconds + 1.second)
    )
)

fun State.takeSecondFromElapsedTime(): State = copy(
    pomodoro = pomodoro.copy(
        elapsedTime = when {
            pomodoro.elapsedTime.milliseconds < 1.second -> Time(minute = 0, second = 0)
            else -> Time(milliseconds = pomodoro.elapsedTime.milliseconds - 1.second)
        }
    )
)
