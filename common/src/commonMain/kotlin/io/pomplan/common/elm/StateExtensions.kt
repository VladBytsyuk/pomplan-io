package io.pomplan.common.elm

import io.pomplan.common.domain.Time
import io.pomplan.common.domain.Timer
import io.pomplan.common.domain.second


fun State.changeTimerMode(mode: Timer.Mode): State = copy(
    timer = timer.copy(mode = mode)
)

fun State.changeGoalTime(time: Time): State = copy(
    timer = timer.copy(goalTime = time)
)

fun State.changeElapsedTime(time: Time): State = copy(
    timer = timer.copy(elapsedTime = time)
)

fun State.addSecondToElapsedTime(): State = copy(
    timer = timer.copy(
        elapsedTime = Time(milliseconds = timer.elapsedTime.milliseconds + 1.second)
    )
)

fun State.takeSecondFromElapsedTime(): State = copy(
    timer = timer.copy(
        elapsedTime = when {
            timer.elapsedTime.milliseconds < 1.second -> Time(minute = 0, second = 0)
            else -> Time(milliseconds = timer.elapsedTime.milliseconds - 1.second)
        }
    )
)
