package io.pomplan.desktop

import io.kotest.core.spec.style.ShouldSpec
import io.pomplan.common.domain.*
import io.pomplan.common.elm.*
import io.pomplan.common.elm.State.Mode.*
import io.pomplan.common.elm.ext.*


class `State modification - single action` : ShouldSpec({
    val settings = Settings(
        workTime = Time(minute = 25),
        shortBreakTime = Time(minute = 5),
        longBreakTime = Time(minute = 15),
        groupSize = 4
    )

    context(name = "Initial state") {
        val initialState = State.initial

        should(
            be = "in the same state after pause initial state",
            actual = initialState.pause(),
            expected = initialState
        )
        
        should(
            be = "in the same state after stop initial state", 
            actual = initialState.stop(), 
            expected = initialState
        )
        
        should(
            be = "in the same state after tick initial state",
            actual = initialState.tick(),
            expected = initialState
        )

        should(
            be = "in work mode after play initial state",
            actual = initialState.play(),
            expected = State(
                mode = TIMER,
                pomodoro = Pomodoro(
                    mode = Pomodoro.Mode.WORK,
                    goalTime = Time(minute = 25),
                    elapsedTime = Time(minute = 25),
                    lastDoneNumber = 0
                ),
                settings = settings
            )
        )
        should(
            be = "in pre-break mode with 1 done pomodoro after skip initial state",
            actual = initialState.skip(),
            expected = State(
                mode = TIMER,
                pomodoro = Pomodoro(
                    mode = Pomodoro.Mode.PRE_BREAK,
                    goalTime = Time(minute = 5),
                    elapsedTime = Time(minute = 5),
                    lastDoneNumber = 1
                ),
                settings = settings
            )
        )
    }

    context(name = "Pre-work state") {
        val preWorkState = State(
            mode = TIMER,
            pomodoro = Pomodoro(
                mode = Pomodoro.Mode.PRE_WORK,
                goalTime = Time(minute = 25),
                elapsedTime = Time(minute = 25),
                lastDoneNumber = 1
            ),
            settings = settings
        )

        should(
            be = "in the work state after play pre-work state",
            actual = preWorkState.play(),
            expected = State(
                mode = TIMER,
                pomodoro = Pomodoro(
                    mode = Pomodoro.Mode.WORK,
                    goalTime = Time(minute = 25),
                    elapsedTime = Time(minute = 25),
                    lastDoneNumber = 1
                ),
                settings = settings
            )
        )
        should(
            be = "in the same state after pause pre-work state",
            actual = preWorkState.pause(),
            expected = preWorkState
        )
        should(
            be = "in the same state after stop pre-work state",
            actual = preWorkState.stop(),
            expected = preWorkState
        )
        should(
            be = "in the same state after tick pre-work state",
            actual = preWorkState.tick(),
            expected = preWorkState
        )
        should(
            be = "in pre-break mode with 2 done pomodoro after skip pre-work state",
            actual = preWorkState.skip(),
            expected = State(
                mode = TIMER,
                pomodoro = Pomodoro(
                    mode = Pomodoro.Mode.PRE_BREAK,
                    goalTime = Time(minute = 5),
                    elapsedTime = Time(minute = 5),
                    lastDoneNumber = 2
                ),
                settings = settings
            )
        )
    }

    context(name = "Work state") {
        val workState = State(
            mode = TIMER,
            pomodoro = Pomodoro(
                mode = Pomodoro.Mode.WORK,
                goalTime = Time(minute = 25),
                elapsedTime = Time(minute = 24, second = 45),
                lastDoneNumber = 0
            ),
            settings = settings
        )

        should(
            be = "in the pre-work state after pause work state",
            actual = workState.pause(),
            expected = State(
                mode = TIMER,
                pomodoro = Pomodoro(
                    mode = Pomodoro.Mode.PRE_WORK,
                    goalTime = Time(minute = 25),
                    elapsedTime = Time(minute = 24, second = 45),
                    lastDoneNumber = 0
                ),
                settings = settings
            )
        )
        should(
            be = "in the pre-work state after stop work state",
            actual = workState.stop(),
            expected = State(
                mode = TIMER,
                pomodoro = Pomodoro(
                    mode = Pomodoro.Mode.PRE_WORK,
                    goalTime = Time(minute = 25),
                    elapsedTime = Time(minute = 25),
                    lastDoneNumber = 0
                ),
                settings = settings
            )
        )
        should(
            be = "in the work state after tick work state",
            actual = workState.tick(),
            expected = State(
                mode = TIMER,
                pomodoro = Pomodoro(
                    mode = Pomodoro.Mode.WORK,
                    goalTime = Time(minute = 25),
                    elapsedTime = Time(minute = 24, second = 44),
                    lastDoneNumber = 0
                ),
                settings = settings
            )
        )
        should(
            be = "in the same state after play work state",
            actual = workState.play(),
            expected = workState
        )
        should(
            be = "in pre-break state with 1 done pomodoro after skip work state",
            actual = workState.skip(),
            expected = State(
                mode = TIMER,
                pomodoro = Pomodoro(
                    mode = Pomodoro.Mode.PRE_BREAK,
                    goalTime = Time(minute = 5),
                    elapsedTime = Time(minute = 5),
                    lastDoneNumber = 1
                ),
                settings = settings
            )
        )
    }

    context(name = "Pre-break state") {
        val preBreakState = State(
            mode = TIMER,
            pomodoro = Pomodoro(
                mode = Pomodoro.Mode.PRE_BREAK,
                goalTime = Time(minute = 5),
                elapsedTime = Time(minute = 5),
                lastDoneNumber = 1
            ),
            settings = settings
        )

        should(
            be = "in the break state after play pre-break state",
            actual = preBreakState.play(),
            expected = State(
                mode = TIMER,
                pomodoro = Pomodoro(
                    mode = Pomodoro.Mode.BREAK,
                    goalTime = Time(minute = 5),
                    elapsedTime = Time(minute = 5),
                    lastDoneNumber = 1
                ),
                settings = settings
            )
        )
        should(
            be = "in the same state after pause pre-break state",
            actual = preBreakState.pause(),
            expected = preBreakState
        )
        should(
            be = "in the same state after stop pre-break state",
            actual = preBreakState.stop(),
            expected = preBreakState
        )
        should(
            be = "in the same state after tick pre-break state",
            actual = preBreakState.tick(),
            expected = preBreakState
        )
        should(
            be = "in pre-work mode with 1 done pomodoro after skip pre-break state",
            actual = preBreakState.skip(),
            expected = State(
                mode = TIMER,
                pomodoro = Pomodoro(
                    mode = Pomodoro.Mode.PRE_WORK,
                    goalTime = Time(minute = 25),
                    elapsedTime = Time(minute = 25),
                    lastDoneNumber = 1
                ),
                settings = settings
            )
        )
    }

    context(name = "Short-break state") {
        val shortBreakState = State(
            mode = TIMER,
            pomodoro = Pomodoro(
                mode = Pomodoro.Mode.BREAK,
                goalTime = Time(minute = 5),
                elapsedTime = Time(minute = 4, second = 55),
                lastDoneNumber = 1
            ),
            settings = settings
        )

        should(
            be = "in the pre-work state after pause short-break state",
            actual = shortBreakState.pause(),
            expected = State(
                mode = TIMER,
                pomodoro = Pomodoro(
                    mode = Pomodoro.Mode.PRE_BREAK,
                    goalTime = Time(minute = 5),
                    elapsedTime = Time(minute = 4, second = 55),
                    lastDoneNumber = 1
                ),
                settings = settings
            )
        )
        should(
            be = "in the pre-break state after stop short-break state",
            actual = shortBreakState.stop(),
            expected = State(
                mode = TIMER,
                pomodoro = Pomodoro(
                    mode = Pomodoro.Mode.PRE_BREAK,
                    goalTime = Time(minute = 5),
                    elapsedTime = Time(minute = 5),
                    lastDoneNumber = 1
                ),
                settings = settings
            )
        )
        should(
            be = "in the work state after tick short-break state",
            actual = shortBreakState.tick(),
            expected = State(
                mode = TIMER,
                pomodoro = Pomodoro(
                    mode = Pomodoro.Mode.BREAK,
                    goalTime = Time(minute = 5),
                    elapsedTime = Time(minute = 4, second = 54),
                    lastDoneNumber = 1
                ),
                settings = settings
            )
        )
        should(
            be = "in the same state after play short-break state",
            actual = shortBreakState.play(),
            expected = shortBreakState
        )
        should(
            be = "in pre-work state with 1 done pomodoro after skip short-break state",
            actual = shortBreakState.skip(),
            expected = State(
                mode = TIMER,
                pomodoro = Pomodoro(
                    mode = Pomodoro.Mode.PRE_WORK,
                    goalTime = Time(minute = 25),
                    elapsedTime = Time(minute = 25),
                    lastDoneNumber = 1
                ),
                settings = settings
            )
        )
    }

    context(name = "Long-break state") {
        val shortBreakState = State(
            mode = TIMER,
            pomodoro = Pomodoro(
                mode = Pomodoro.Mode.BREAK,
                goalTime = Time(minute = 15),
                elapsedTime = Time(minute = 14, second = 55),
                lastDoneNumber = 4
            ),
            settings = settings
        )

        should(
            be = "in the pre-work state after pause long-break state",
            actual = shortBreakState.pause(),
            expected = State(
                mode = TIMER,
                pomodoro = Pomodoro(
                    mode = Pomodoro.Mode.PRE_BREAK,
                    goalTime = Time(minute = 15),
                    elapsedTime = Time(minute = 14, second = 55),
                    lastDoneNumber = 4
                ),
                settings = settings
            )
        )
        should(
            be = "in the pre-break state after stop long-break state",
            actual = shortBreakState.stop(),
            expected = State(
                mode = TIMER,
                pomodoro = Pomodoro(
                    mode = Pomodoro.Mode.PRE_BREAK,
                    goalTime = Time(minute = 15),
                    elapsedTime = Time(minute = 15),
                    lastDoneNumber = 4
                ),
                settings = settings
            )
        )
        should(
            be = "in the work state after tick long-break state",
            actual = shortBreakState.tick(),
            expected = State(
                mode = TIMER,
                pomodoro = Pomodoro(
                    mode = Pomodoro.Mode.BREAK,
                    goalTime = Time(minute = 15),
                    elapsedTime = Time(minute = 14, second = 54),
                    lastDoneNumber = 4
                ),
                settings = settings
            )
        )
        should(
            be = "in the same state after play long-break state",
            actual = shortBreakState.play(),
            expected = shortBreakState
        )
        should(
            be = "in pre-work state with 4 done pomodoro after skip long-break state",
            actual = shortBreakState.skip(),
            expected = State(
                mode = TIMER,
                pomodoro = Pomodoro(
                    mode = Pomodoro.Mode.PRE_WORK,
                    goalTime = Time(minute = 25),
                    elapsedTime = Time(minute = 25),
                    lastDoneNumber = 4
                ),
                settings = settings
            )
        )
    }
})
