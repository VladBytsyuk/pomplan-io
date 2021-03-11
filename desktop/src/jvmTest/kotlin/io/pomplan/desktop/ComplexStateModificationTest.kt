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
            name = "be a long-break state after 7 skip actions",
            actual = initialState
                .multiOperation(times = 7) { it.skip() },
            expected = State(
                pomodoro = Pomodoro(
                    mode = Pomodoro.Mode.PRE_BREAK,
                    goalTime = longBreakTime,
                    elapsedTime = longBreakTime,
                    lastDoneNumber = 4
                )
            )
        )

        val ticksToWorkEnd = workTime.minute * SECONDS_IN_MINUTE.toInt()
        should(
            name = "be a work state after play action and $ticksToWorkEnd ticks",
            actual = initialState
                .play()
                .multiOperation(times = ticksToWorkEnd) { it.tick() },
            expected = State(
                pomodoro = Pomodoro(
                    mode = Pomodoro.Mode.WORK,
                    goalTime = workTime,
                    elapsedTime = Time(minute = 0, second = 0),
                    lastDoneNumber = 0
                )
            )
        )

        val ticksToPreBreak = ticksToWorkEnd + 1
        should(
            name = "be a pre-break state after play action and $ticksToPreBreak ticks",
            actual = initialState
                .play()
                .multiOperation(times = ticksToPreBreak) { it.tick() },
            expected = State(
                pomodoro = Pomodoro(
                    mode = Pomodoro.Mode.PRE_BREAK,
                    goalTime = shortBreakTime,
                    elapsedTime = shortBreakTime,
                    lastDoneNumber = 1
                )
            )
        )
    }
})
