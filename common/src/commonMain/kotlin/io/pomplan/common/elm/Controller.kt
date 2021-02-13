package io.pomplan.common.elm

import io.pomplan.common.Logger
import io.pomplan.common.LoggerImpl
import io.pomplan.common.PomodoroTimer
import io.pomplan.common.domain.Pomodoro
import io.pomplan.common.domain.Pomodoro.Mode.*
import io.pomplan.common.domain.workTime


class Controller(
    logger: Logger,
    pomodoroTimer: PomodoroTimer
) : Elm.Controller<State, Action, Effect> by Elm.ControllerImpl(
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
        logger = logger,
        timer = pomodoroTimer,
    ),
    reducer = Reducer(),
    converterFactory = ViewDataConverterFactory(),
    logger =logger
)
