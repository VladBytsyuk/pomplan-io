package io.pomplan.common.elm

import io.pomplan.common.domain.Pomodoro.Mode.*
import io.pomplan.common.elm.Action.*


class Reducer : Elm.Reducer<State, Action, Effect> {
    override fun illegalStateEffect(state: State, action: Action): Effect =
        Effect.IllegalState(state, action)

    override fun allowedCondition(state: State, action: Action): Boolean = when (action) {
        UserClick.Button.Stop,
        UserClick.Button.Skip -> true
        UserClick.Button.Play -> state.pomodoro.mode in listOf(PRE_WORK, PRE_BREAK)
        UserClick.Button.Pause -> state.pomodoro.mode in listOf(WORK, BREAK)
    }

    override fun reduceValid(state: State, action: Action): Pair<State, Effect?> = when (action) {
        UserClick.Button.Stop -> state.stopPomodoro() to null
        UserClick.Button.Skip -> state.skipPomodoro() to null
        UserClick.Button.Play -> state.playPomodoro() to Effect.Timer.Run
        UserClick.Button.Pause -> state.pausePomodoro() to Effect.Timer.Stop
    }
}
