package io.pomplan.desktop

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.pomplan.common.domain.Pomodoro
import io.pomplan.common.domain.Pomodoro.Mode.*
import io.pomplan.common.domain.Time
import io.pomplan.common.elm.*
import io.pomplan.common.kodein
import org.kodein.di.instance


class StateModificationTest : ShouldSpec({
    should("2 + 2 = 4") {
        (2 + 2) shouldBe 4
    }

    context(name = "Initial state") {
        val initialState = State.initial
        should("be in work mode after play") {
            initialState.playPomodoro() shouldBe State(
                pomodoro = Pomodoro(
                    mode = WORK,
                    goalTime = Time(minute = 25),
                    elapsedTime = Time(minute = 25),
                    lastDoneNumber = 0
                )
            )
        }
        should("be in the same state after pause") {
            initialState.pausePomodoro() shouldBe initialState
        }
        should("be in the same state after stop") {
            initialState.stopPomodoro() shouldBe initialState
        }
        should("be in pre break mode with 1 done pomodoro after skip") {
            initialState.skipPomodoro() shouldBe State(
                pomodoro = Pomodoro(
                    mode = PRE_BREAK,
                    goalTime = Time(minute = 5),
                    elapsedTime = Time(minute = 5),
                    lastDoneNumber = 1
                )
            )
        }
        should("be in the same state after tick") {
            initialState.tick() shouldBe initialState
        }
    }
})
