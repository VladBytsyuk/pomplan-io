package io.pomplan.desktop

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.pomplan.common.domain.*
import io.pomplan.common.elm.*


class `State modification - single action` : ShouldSpec({

    context(name = "Initial state") {
        val initialState = State.initial

        should("be in the same state after pause initial state") {
            initialState.pause() shouldBe initialState
        }
        should("be in the same state after stop initial state") {
            initialState.stop() shouldBe initialState
        }
        should("be in the same state after tick initial state") {
            initialState.tick() shouldBe initialState
        }
        should("be in work mode after play initial state") {
            initialState.play() shouldBe State(
                pomodoro = Pomodoro(
                    mode = Pomodoro.Mode.WORK,
                    goalTime = workTime,
                    elapsedTime = workTime,
                    lastDoneNumber = 0
                )
            )
        }
        should("be in pre-break mode with 1 done pomodoro after skip initial state") {
            initialState.skip() shouldBe State(
                pomodoro = Pomodoro(
                    mode = Pomodoro.Mode.PRE_BREAK,
                    goalTime = shortBreakTime,
                    elapsedTime = shortBreakTime,
                    lastDoneNumber = 1
                )
            )
        }
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

        should("be in the work state after play pre-work state") {
            preWorkState.play() shouldBe State(
                pomodoro = Pomodoro(
                    mode = Pomodoro.Mode.WORK,
                    goalTime = workTime,
                    elapsedTime = workTime,
                    lastDoneNumber = 1
                )
            )
        }
        should("be in the same state after pause pre-work state") {
            preWorkState.pause() shouldBe preWorkState
        }
        should("be in the same state after stop pre-work state") {
            preWorkState.stop() shouldBe preWorkState
        }
        should("be in the same state after tick pre-work state") {
            preWorkState.tick() shouldBe preWorkState
        }
        should("be in pre-break mode with 2 done pomodoro after skip pre-work state") {
            preWorkState.skip() shouldBe State(
                pomodoro = Pomodoro(
                    mode = Pomodoro.Mode.PRE_BREAK,
                    goalTime = shortBreakTime,
                    elapsedTime = shortBreakTime,
                    lastDoneNumber = 2
                )
            )
        }
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

        should("be in the pre-work state after pause work state") {
            workState.pause() shouldBe State(
                pomodoro = Pomodoro(
                    mode = Pomodoro.Mode.PRE_WORK,
                    goalTime = workTime,
                    elapsedTime = Time(workTime.milliseconds - 15_000),
                    lastDoneNumber = 0
                )
            )
        }
        should("be in the pre-work state after stop work state") {
            workState.stop() shouldBe State(
                pomodoro = Pomodoro(
                    mode = Pomodoro.Mode.PRE_WORK,
                    goalTime = workTime,
                    elapsedTime = workTime,
                    lastDoneNumber = 0
                )
            )
        }
        should("be in the work state after tick work state") {
            workState.tick() shouldBe State(
                pomodoro = Pomodoro(
                    mode = Pomodoro.Mode.WORK,
                    goalTime = workTime,
                    elapsedTime = Time(workTime.milliseconds - 16_000),
                    lastDoneNumber = 0
                )
            )
        }
        should("be in the same state after play work state") {
            workState.play() shouldBe workState
        }
        should("be in pre-break state with 1 done pomodoro after skip work state") {
            workState.skip() shouldBe State(
                pomodoro = Pomodoro(
                    mode = Pomodoro.Mode.PRE_BREAK,
                    goalTime = shortBreakTime,
                    elapsedTime = shortBreakTime,
                    lastDoneNumber = 1
                )
            )
        }
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

        should("be in the break state after play pre-break state") {
            preBreakState.play() shouldBe State(
                pomodoro = Pomodoro(
                    mode = Pomodoro.Mode.BREAK,
                    goalTime = shortBreakTime,
                    elapsedTime = shortBreakTime,
                    lastDoneNumber = 1
                )
            )
        }
        should("be in the same state after pause pre-break state") {
            preBreakState.pause() shouldBe preBreakState
        }
        should("be in the same state after stop pre-break state") {
            preBreakState.stop() shouldBe preBreakState
        }
        should("be in the same state after tick pre-break state") {
            preBreakState.tick() shouldBe preBreakState
        }
        should("be in pre-work mode with 1 done pomodoro after skip pre-break state") {
            preBreakState.skip() shouldBe State(
                pomodoro = Pomodoro(
                    mode = Pomodoro.Mode.PRE_WORK,
                    goalTime = workTime,
                    elapsedTime = workTime,
                    lastDoneNumber = 1
                )
            )
        }
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

        should("be in the pre-work state after pause short-break state") {
            shortBreakState.pause() shouldBe State(
                pomodoro = Pomodoro(
                    mode = Pomodoro.Mode.PRE_BREAK,
                    goalTime = shortBreakTime,
                    elapsedTime = Time(shortBreakTime.milliseconds - 5_000),
                    lastDoneNumber = 1
                )
            )
        }
        should("be in the pre-break state after stop short-break state") {
            shortBreakState.stop() shouldBe State(
                pomodoro = Pomodoro(
                    mode = Pomodoro.Mode.PRE_BREAK,
                    goalTime = shortBreakTime,
                    elapsedTime = shortBreakTime,
                    lastDoneNumber = 1
                )
            )
        }
        should("be in the work state after tick short-break state") {
            shortBreakState.tick() shouldBe State(
                pomodoro = Pomodoro(
                    mode = Pomodoro.Mode.BREAK,
                    goalTime = shortBreakTime,
                    elapsedTime = Time(shortBreakTime.milliseconds - 6_000),
                    lastDoneNumber = 1
                )
            )
        }
        should("be in the same state after play short-break state") {
            shortBreakState.play() shouldBe shortBreakState
        }
        should("be in pre-work state with 1 done pomodoro after skip short-break state") {
            shortBreakState.skip() shouldBe State(
                pomodoro = Pomodoro(
                    mode = Pomodoro.Mode.PRE_WORK,
                    goalTime = workTime,
                    elapsedTime = workTime,
                    lastDoneNumber = 1
                )
            )
        }
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

        should("be in the pre-work state after pause long-break state") {
            shortBreakState.pause() shouldBe State(
                pomodoro = Pomodoro(
                    mode = Pomodoro.Mode.PRE_BREAK,
                    goalTime = longBreakTime,
                    elapsedTime = Time(longBreakTime.milliseconds - 5_000),
                    lastDoneNumber = 4
                )
            )
        }
        should("be in the pre-break state after stop long-break state") {
            shortBreakState.stop() shouldBe State(
                pomodoro = Pomodoro(
                    mode = Pomodoro.Mode.PRE_BREAK,
                    goalTime = longBreakTime,
                    elapsedTime = longBreakTime,
                    lastDoneNumber = 4
                )
            )
        }
        should("be in the work state after tick long-break state") {
            shortBreakState.tick() shouldBe State(
                pomodoro = Pomodoro(
                    mode = Pomodoro.Mode.BREAK,
                    goalTime = longBreakTime,
                    elapsedTime = Time(longBreakTime.milliseconds - 6_000),
                    lastDoneNumber = 4
                )
            )
        }
        should("be in the same state after play long-break state") {
            shortBreakState.play() shouldBe shortBreakState
        }
        should("be in pre-work state with 4 done pomodoro after skip long-break state") {
            shortBreakState.skip() shouldBe State(
                pomodoro = Pomodoro(
                    mode = Pomodoro.Mode.PRE_WORK,
                    goalTime = workTime,
                    elapsedTime = workTime,
                    lastDoneNumber = 4
                )
            )
        }
    }
})
