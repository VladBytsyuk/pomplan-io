package io.pomplan.common.elm

import io.pomplan.common.Logger
import io.pomplan.common.Timer
import io.pomplan.common.domain.Pomodoro
import io.pomplan.common.domain.Pomodoro.Mode.*
import io.pomplan.common.domain.workTime


class Controller(
    logger: Logger,
    val timer: Timer
) : Elm.Controller<State, Action, Effect> by Elm.ControllerImpl(
    initialState = State.initial,
    initialAction = Action.Initial,
    effectHandler = EffectHandler(logger = logger, timer = timer,),
    reducer = Reducer(),
    converterFactory = ViewDataConverterFactory(),
    logger = logger
)
