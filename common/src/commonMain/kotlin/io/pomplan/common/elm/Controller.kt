package io.pomplan.common.elm

import io.pomplan.common.Logger
import io.pomplan.common.Repository
import io.pomplan.common.Timer
import io.pomplan.common.domain.Settings


class Controller(
    logger: Logger,
    val timer: Timer,
    settingsRepository: Repository<Settings>,
) : Elm.Controller<State, Action, Effect> by Elm.ControllerImpl(
    initialState = State.initial,
    initialAction = Action.Initial,
    effectHandler = EffectHandler(
        logger = logger,
        timer = timer,
        settingsRepository = settingsRepository
    ),
    reducer = Reducer(),
    converterFactory = ViewDataConverterFactory(),
    logger = logger
)
