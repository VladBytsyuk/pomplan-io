package io.pomplan.android

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.pomplan.common.domain.Pomodoro
import io.pomplan.common.domain.Pomodoro.Mode.*
import io.pomplan.common.domain.Time
import io.pomplan.common.domain.shortBreakTime
import io.pomplan.common.domain.workTime
import io.pomplan.common.elm.*


class StateModificationTest : ShouldSpec({
    context(name = "Initial state") {
        val initialState = State.initial
        should("be in the same state after pause") { initialState.pausePomodoro() shouldBe initialState }
        should("be in the same state after stop") { initialState.stopPomodoro() shouldBe initialState }
        should("be in the same state after tick") { initialState.tick() shouldBe initialState }
        should("be in work mode after play") {
            initialState.playPomodoro() shouldBe State(
                pomodoro = Pomodoro(
                    mode = WORK,
                    goalTime = workTime,
                    elapsedTime = workTime,
                    lastDoneNumber = 0
                )
            )
        }
        should("be in pre break mode with 1 done pomodoro after skip") {
            initialState.skipPomodoro() shouldBe State(
                pomodoro = Pomodoro(
                    mode = PRE_BREAK,
                    goalTime = shortBreakTime,
                    elapsedTime = shortBreakTime,
                    lastDoneNumber = 1
                )
            )
        }
    }
    context(name = "Work state") {
        val workState = State(
            pomodoro = Pomodoro(
                mode = WORK,
                goalTime = workTime,
                elapsedTime = Time(workTime.milliseconds - 15_000),
                lastDoneNumber = 0
            )
        )
        should("be in the pre work state after pause") {
            workState.pausePomodoro() shouldBe State(
                pomodoro = Pomodoro(
                    mode = PRE_WORK,
                    goalTime = workTime,
                    elapsedTime = Time(workTime.milliseconds - 15_000),
                    lastDoneNumber = 0
                )
            )
        }
        should("be in the pre work state after stop") {
            workState.stopPomodoro() shouldBe State(
                pomodoro = Pomodoro(
                    mode = PRE_WORK,
                    goalTime = workTime,
                    elapsedTime = workTime,
                    lastDoneNumber = 0
                )
            )
        }
        should("be in the work state with decreased elapsed time after tick") {
            workState.tick() shouldBe State(
                pomodoro = Pomodoro(
                    mode = WORK,
                    goalTime = workTime,
                    elapsedTime = Time(workTime.milliseconds - 16_000),
                    lastDoneNumber = 0
                )
            )
        }
        should("be in the same mode after play") { workState.playPomodoro() shouldBe workState }
        should("be  in pre break mode with 1 done pomodoro after skip") {
            workState.skipPomodoro() shouldBe State(
                pomodoro = Pomodoro(
                    mode = PRE_BREAK,
                    goalTime = shortBreakTime,
                    elapsedTime = shortBreakTime,
                    lastDoneNumber = 1
                )
            )
        }
    }
})
