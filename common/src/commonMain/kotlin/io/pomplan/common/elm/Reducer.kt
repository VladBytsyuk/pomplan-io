package io.pomplan.common.elm

import io.pomplan.common.domain.Pomodoro.Mode.*
import io.pomplan.common.domain.done
import io.pomplan.common.elm.Action.*
import io.pomplan.common.elm.Action.UserClick.Button.*
import io.pomplan.common.elm.State.Mode.*
import io.pomplan.common.elm.ext.*


class Reducer : Elm.Reducer<State, Action, Effect> {
    override fun illegalStateEffect(state: State, action: Action): Effect =
        Effect.IllegalState(state, action)

    override fun allowedCondition(state: State, action: Action): Boolean = when (action) {
        Initial -> true
        Stop, is Skip -> state.mode == TIMER
        Play -> state.mode == TIMER && state.pomodoro.mode in listOf(PRE_WORK, PRE_BREAK)
        is TimerTick, Pause -> state.mode == TIMER && state.pomodoro.mode in listOf(WORK, BREAK)
        is Settings -> state.mode == SETTINGS
    }


    override fun reduceValid(state: State, action: Action): Pair<State, Effect?> = when (action) {
        Initial -> state to null
        is Settings -> reduceValid(state, action)
        is UserClick.Button -> reduceValid(state, action)
        is TimerTick -> state.tick() to (if (state.pomodoro.done) Effect.Timer.Stop else null)
    }

    private fun reduceValid(state: State, action: UserClick.Button): Pair<State, Effect?> = when (action) {
        Stop -> state.stop() to Effect.Timer.Stop
        is Skip -> state.skip() to Effect.Timer.Stop
        Play -> state.play() to Effect.Timer.Run
        Pause -> state.pause() to Effect.Timer.Stop
    }

    private fun reduceValid(state: State, action: Settings): Pair<State, Effect?> = when (action) {
        is Settings.Change.Time.Work -> state.updateSettings(workTime = action.newTime) to null
        is Settings.Change.Time.ShortBreak -> state.updateSettings(shortBreakTime = action.newTime) to null
        is Settings.Change.Time.LongBreak -> state.updateSettings(longBreakTime = action.newTime) to null
        is Settings.Change.GroupSize -> state.updateSettings(groupSize = action.newGroupSize) to null
    }
}
