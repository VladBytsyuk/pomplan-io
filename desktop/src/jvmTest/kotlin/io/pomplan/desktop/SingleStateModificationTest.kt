package io.pomplan.desktop

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.pomplan.common.domain.*
import io.pomplan.common.elm.*


class `State modification - single action` : ShouldSpec({

    context(name = "Initial state") {
        val initialState = State.initial

        should(
            name = "be in the same state after pause initial state",
            actual = initialState.pause(),
            expected = initialState
        )
        
        should(
            name = "be in the same state after stop initial state", 
            actual = initialState.stop(), 
            expected = initialState
        )
        
        should(
            name = "be in the same state after tick initial state",
            actual = initialState.tick(),
            expected = initialState
        )

        should(
            name = "be in work mode after play initial state",
            actual = initialState.play(),
            expected = State(
                pomodoro = Pomodoro(
                    mode = Pomodoro.Mode.WORK,
                    goalTime = workTime,
                    elapsedTime = workTime,
                    lastDoneNumber = 0
                )
            )
        )
        should(
            name = "be in pre-break mode with 1 done pomodoro after skip initial state",
            actual = initialState.skip(),
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

    context(name = "Pre-work state") {
        val preWorkState = State(
            pomodoro = Pomodoro(
                mode = Pomodoro.Mode.PRE_WORK,
                goalTime = workTime,
                elapsedTime = workTime,
                lastDoneNumber = 1
            )
        )

        should(
            name = "be in the work state after play pre-work state",
            actual = preWorkState.play(),
            expected = State(
                pomodoro = Pomodoro(
                    mode = Pomodoro.Mode.WORK,
                    goalTime = workTime,
                    elapsedTime = workTime,
                    lastDoneNumber = 1
                )
            )
        )
        should(
            name = "be in the same state after pause pre-work state",
            actual = preWorkState.pause(),
            expected = preWorkState
        )
        should(
            name = "be in the same state after stop pre-work state",
            actual = preWorkState.stop(),
            expected = preWorkState
        )
        should(
            name = "be in the same state after tick pre-work state",
            actual = preWorkState.tick(),
            expected = preWorkState
        )
        should(
            name = "be in pre-break mode with 2 done pomodoro after skip pre-work state",
            actual = preWorkState.skip(),
            expected = State(
                pomodoro = Pomodoro(
                    mode = Pomodoro.Mode.PRE_BREAK,
                    goalTime = shortBreakTime,
                    elapsedTime = shortBreakTime,
                    lastDoneNumber = 2
                )
            )
        )
    }

    context(name = "Work state") {
        val workState = State(
            pomodoro = Pomodoro(
                mode = Pomodoro.Mode.WORK,
                goalTime = workTime,
                elapsedTime = Time(workTime.milliseconds - 15_000),
                lastDoneNumber = 0
            )
        )

        should(
            name = "be in the pre-work state after pause work state",
            actual = workState.pause(),
            expected = State(
                pomodoro = Pomodoro(
                    mode = Pomodoro.Mode.PRE_WORK,
                    goalTime = workTime,
                    elapsedTime = Time(workTime.milliseconds - 15_000),
                    lastDoneNumber = 0
                )
            )
        )
        should(
            name = "be in the pre-work state after stop work state",
            actual = workState.stop(),
            expected = State(
                pomodoro = Pomodoro(
                    mode = Pomodoro.Mode.PRE_WORK,
                    goalTime = workTime,
                    elapsedTime = workTime,
                    lastDoneNumber = 0
                )
            )
        )
        should(
            name = "be in the work state after tick work state",
            actual = workState.tick(),
            expected = State(
                pomodoro = Pomodoro(
                    mode = Pomodoro.Mode.WORK,
                    goalTime = workTime,
                    elapsedTime = Time(workTime.milliseconds - 16_000),
                    lastDoneNumber = 0
                )
            )
        )
        should(
            name = "be in the same state after play work state",
            actual = workState.play(),
            expected = workState
        )
        should(
            name = "be in pre-break state with 1 done pomodoro after skip work state",
            actual = workState.skip(),
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

    context(name = "Pre-break state") {
        val preBreakState = State(
            pomodoro = Pomodoro(
                mode = Pomodoro.Mode.PRE_BREAK,
                goalTime = shortBreakTime,
                elapsedTime = shortBreakTime,
                lastDoneNumber = 1
            )
        )

        should(
            name = "be in the break state after play pre-break state",
            actual = preBreakState.play(),
            expected = State(
                pomodoro = Pomodoro(
                    mode = Pomodoro.Mode.BREAK,
                    goalTime = shortBreakTime,
                    elapsedTime = shortBreakTime,
                    lastDoneNumber = 1
                )
            )
        )
        should(
            name = "be in the same state after pause pre-break state",
            actual = preBreakState.pause(),
            expected = preBreakState
        )
        should(
            name = "be in the same state after stop pre-break state",
            actual = preBreakState.stop(),
            expected = preBreakState
        )
        should(
            name = "be in the same state after tick pre-break state",
            actual = preBreakState.tick(),
            expected = preBreakState
        )
        should(
            name = "be in pre-work mode with 1 done pomodoro after skip pre-break state",
            actual = preBreakState.skip(),
            expected = State(
                pomodoro = Pomodoro(
                    mode = Pomodoro.Mode.PRE_WORK,
                    goalTime = workTime,
                    elapsedTime = workTime,
                    lastDoneNumber = 1
                )
            )
        )
    }

    context(name = "Short-break state") {
        val shortBreakState = State(
            pomodoro = Pomodoro(
                mode = Pomodoro.Mode.BREAK,
                goalTime = shortBreakTime,
                elapsedTime = Time(shortBreakTime.milliseconds - 5_000),
                lastDoneNumber = 1
            )
        )

        should(
            name = "be in the pre-work state after pause short-break state",
            actual = shortBreakState.pause(),
            expected = State(
                pomodoro = Pomodoro(
                    mode = Pomodoro.Mode.PRE_BREAK,
                    goalTime = shortBreakTime,
                    elapsedTime = Time(shortBreakTime.milliseconds - 5_000),
                    lastDoneNumber = 1
                )
            )
        )
        should(
            name = "be in the pre-break state after stop short-break state",
            actual = shortBreakState.stop(),
            expected = State(
                pomodoro = Pomodoro(
                    mode = Pomodoro.Mode.PRE_BREAK,
                    goalTime = shortBreakTime,
                    elapsedTime = shortBreakTime,
                    lastDoneNumber = 1
                )
            )
        )
        should(
            name = "be in the work state after tick short-break state",
            actual = shortBreakState.tick(),
            expected = State(
                pomodoro = Pomodoro(
                    mode = Pomodoro.Mode.BREAK,
                    goalTime = shortBreakTime,
                    elapsedTime = Time(shortBreakTime.milliseconds - 6_000),
                    lastDoneNumber = 1
                )
            )
        )
        should(
            name = "be in the same state after play short-break state",
            actual = shortBreakState.play(),
            expected = shortBreakState
        )
        should(
            name = "be in pre-work state with 1 done pomodoro after skip short-break state",
            actual = shortBreakState.skip(),
            expected = State(
                pomodoro = Pomodoro(
                    mode = Pomodoro.Mode.PRE_WORK,
                    goalTime = workTime,
                    elapsedTime = workTime,
                    lastDoneNumber = 1
                )
            )
        )
    }

    context(name = "Long-break state") {
        val shortBreakState = State(
            pomodoro = Pomodoro(
                mode = Pomodoro.Mode.BREAK,
                goalTime = longBreakTime,
                elapsedTime = Time(longBreakTime.milliseconds - 5_000),
                lastDoneNumber = 4
            )
        )

        should(
            name = "be in the pre-work state after pause long-break state",
            actual = shortBreakState.pause(),
            expected = State(
                pomodoro = Pomodoro(
                    mode = Pomodoro.Mode.PRE_BREAK,
                    goalTime = longBreakTime,
                    elapsedTime = Time(longBreakTime.milliseconds - 5_000),
                    lastDoneNumber = 4
                )
            )
        )
        should(
            name = "be in the pre-break state after stop long-break state",
            actual = shortBreakState.stop(),
            expected = State(
                pomodoro = Pomodoro(
                    mode = Pomodoro.Mode.PRE_BREAK,
                    goalTime = longBreakTime,
                    elapsedTime = longBreakTime,
                    lastDoneNumber = 4
                )
            )
        )
        should(
            name = "be in the work state after tick long-break state",
            actual = shortBreakState.tick(),
            expected = State(
                pomodoro = Pomodoro(
                    mode = Pomodoro.Mode.BREAK,
                    goalTime = longBreakTime,
                    elapsedTime = Time(longBreakTime.milliseconds - 6_000),
                    lastDoneNumber = 4
                )
            )
        )
        should(
            name = "be in the same state after play long-break state",
            actual = shortBreakState.play(),
            expected = shortBreakState
        )
        should(
            name = "be in pre-work state with 4 done pomodoro after skip long-break state",
            actual = shortBreakState.skip(),
            expected = State(
                pomodoro = Pomodoro(
                    mode = Pomodoro.Mode.PRE_WORK,
                    goalTime = workTime,
                    elapsedTime = workTime,
                    lastDoneNumber = 4
                )
            )
        )
    }
})
