package io.pomplan.common

import io.pomplan.common.elm.Action
import io.pomplan.common.elm.Effect
import io.pomplan.common.elm.Elm
import io.pomplan.common.elm.State


class PomodoroTimer(controller: Elm.Controller<State, Action, Effect>) :
    Timer({ controller.setAction(Action.TimerTick) })
