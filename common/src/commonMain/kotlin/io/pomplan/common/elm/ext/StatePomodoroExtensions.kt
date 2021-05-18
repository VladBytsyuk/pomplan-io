package io.pomplan.common.elm.ext

import io.pomplan.common.domain.*
import io.pomplan.common.domain.Pomodoro.Mode.*
import io.pomplan.common.elm.State


fun State.stop(): State = when (pomodoro.mode) {
    PRE_WORK, WORK -> updatePomodoro(mode = PRE_WORK, elapsedTime = pomodoro.goalTime)
    PRE_BREAK, BREAK -> updatePomodoro(mode = PRE_BREAK, elapsedTime = pomodoro.goalTime)
}

fun State.skip(): State = when (pomodoro.mode) {
    WORK, PRE_WORK -> updatePomodoro(
        mode = PRE_BREAK,
        goalTime = nextBreakTime,
        elapsedTime = nextBreakTime,
        lastDoneNumber = pomodoro.number
    )
    BREAK, PRE_BREAK -> updatePomodoro(
        mode = PRE_WORK,
        goalTime = settings.workTime,
        elapsedTime = settings.workTime
    )
}

fun State.play(): State = when (pomodoro.mode) {
    WORK, BREAK -> this
    PRE_WORK -> updatePomodoro(mode = WORK)
    PRE_BREAK -> updatePomodoro(mode = BREAK)
}

fun State.pause(): State = when (pomodoro.mode) {
    PRE_WORK, PRE_BREAK -> this
    WORK -> updatePomodoro(mode = PRE_WORK)
    BREAK -> updatePomodoro(mode = PRE_BREAK)
}


fun State.tick(): State = when (pomodoro.mode) {
    PRE_WORK, PRE_BREAK -> this
    WORK -> when {
        pomodoro.done -> updatePomodoro(
            mode = PRE_BREAK,
            goalTime = nextBreakTime,
            elapsedTime = nextBreakTime,
            lastDoneNumber = pomodoro.number
        )
        else -> takeSecondFromElapsedTime()
    }
    BREAK -> when {
        pomodoro.done -> updatePomodoro(
            mode = PRE_WORK,
            goalTime = settings.workTime,
            elapsedTime = settings.workTime
        )
        else -> takeSecondFromElapsedTime()
    }
}


private fun State.takeSecondFromElapsedTime(): State = updatePomodoro(
    elapsedTime = when {
        pomodoro.elapsedTime < 1.second -> Time(minute = 0, second = 0)
        else -> pomodoro.elapsedTime - 1.second
    }
)

private fun State.updatePomodoro(
    mode: Pomodoro.Mode = pomodoro.mode,
    goalTime: Time = pomodoro.goalTime,
    elapsedTime: Time = pomodoro.elapsedTime,
    lastDoneNumber: Int = pomodoro.lastDoneNumber
): State = copy(
    pomodoro = Pomodoro(mode, goalTime, elapsedTime, lastDoneNumber)
)