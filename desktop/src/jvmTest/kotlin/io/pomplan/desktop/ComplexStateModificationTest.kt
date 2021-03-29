package io.pomplan.desktop

import io.kotest.core.spec.style.ShouldSpec
import io.pomplan.common.domain.*
import io.pomplan.common.elm.State
import io.pomplan.common.elm.play
import io.pomplan.common.elm.skip
import io.pomplan.common.elm.tick


class `State modification - complex action` : ShouldSpec({

    context(name = "Initial state") {
        val initialState = State.initial

        should(
            be = "a long-break state after 7 skip actions",
            actual = initialState
                .multiOperation(times = 7) { it.skip() },
            expected = State(
                pomodoro = Pomodoro(
                    mode = Pomodoro.Mode.PRE_BREAK,
                    goalTime = Time(minute = 15),
                    elapsedTime = Time(minute = 15),
                    lastDoneNumber = 4
                ),
                settings = Settings(
                    workTime = Time(minute = 25),
                    shortBreakTime = Time(minute = 5),
                    longBreakTime = Time(minute = 15),
                    groupSize = 4
                )
            )
        )

        val ticksToWorkEnd = 25 * SECONDS_IN_MINUTE.toInt()
        should(
            be = "a work state after play action and $ticksToWorkEnd ticks",
            actual = initialState
                .play()
                .multiOperation(times = ticksToWorkEnd) { it.tick() },
            expected = State(
                pomodoro = Pomodoro(
                    mode = Pomodoro.Mode.WORK,
                    goalTime = Time(minute = 25),
                    elapsedTime = Time(minute = 0),
                    lastDoneNumber = 0
                ),
                settings = Settings(
                    workTime = Time(minute = 25),
                    shortBreakTime = Time(minute = 5),
                    longBreakTime = Time(minute = 15),
                    groupSize = 4
                )
            )
        )

        val ticksToPreBreak = ticksToWorkEnd + 1
        should(
            be = "a pre-break state after play action and $ticksToPreBreak ticks",
            actual = initialState
                .play()
                .multiOperation(times = ticksToPreBreak) { it.tick() },
            expected = State(
                pomodoro = Pomodoro(
                    mode = Pomodoro.Mode.PRE_BREAK,
                    goalTime = Time(minute = 5),
                    elapsedTime = Time(minute = 5),
                    lastDoneNumber = 1
                ),
                settings = Settings(
                    workTime = Time(minute = 25),
                    shortBreakTime = Time(minute = 5),
                    longBreakTime = Time(minute = 15),
                    groupSize = 4
                )
            )
        )
    }
})
