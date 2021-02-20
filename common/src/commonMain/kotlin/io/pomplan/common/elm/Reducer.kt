package io.pomplan.common.elm

import io.pomplan.common.domain.Pomodoro.Mode.*
import io.pomplan.common.elm.Action.*
import io.pomplan.common.elm.Action.UserClick.Button.*


class Reducer : Elm.Reducer<State, Action, Effect> {
    override fun illegalStateEffect(state: State, action: Action): Effect =
        Effect.IllegalState(state, action)

    override fun allowedCondition(state: State, action: Action): Boolean = when (action) {
        Initial, is Stop, is Skip -> true
        Play -> state.pomodoro.mode in listOf(PRE_WORK, PRE_BREAK)
        is TimerTick, Pause -> state.pomodoro.mode in listOf(WORK, BREAK)
    }


    override fun reduceValid(state: State, action: Action): Pair<State, Effect?> = when (action) {
        Initial -> state to null
        is UserClick.Button -> reduceValid(state, action)
        is TimerTick -> state.tick() to (if (state.pomodoro.done) Effect.Timer.Stop else null)
    }

    private fun reduceValid(state: State, action: UserClick.Button): Pair<State, Effect?> = when (action) {
        is Stop -> state.stopPomodoro() to null
        is Skip -> state.skipPomodoro() to null
        Play -> state.playPomodoro() to Effect.Timer.Run
        Pause -> state.pausePomodoro() to Effect.Timer.Stop
    }
}
