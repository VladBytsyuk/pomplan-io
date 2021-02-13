package io.pomplan.common.elm

import io.pomplan.common.LoggerImpl
import io.pomplan.common.Timer
import io.pomplan.common.domain.Pomodoro
import io.pomplan.common.domain.Pomodoro.Mode.*
import io.pomplan.common.domain.workTime


class Controller : Elm.Controller<State, Action, Effect> by Elm.ControllerImpl(
    initialState = State(
        pomodoro = Pomodoro(
            mode = PRE_WORK,
            goalTime = workTime,
            elapsedTime = workTime,
            lastDoneNumber = 0,
        )
    ),
    initialAction = Action.Initial,
    effectHandler = EffectHandler(
        logger = LoggerImpl(),
        timer = Timer { setAction(Action.TimerTick) },
    ),
    reducer = Reducer(),
    converterFactory = ViewDataConverterFactory(),
    logger = LoggerImpl()
)
