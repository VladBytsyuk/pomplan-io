package io.pomplan.common

import io.pomplan.common.elm.*
import org.kodein.di.*


val kodein get() = container.direct

private val container = DI.lazy { import(coreModule) }

private val coreModule = DI.Module(name = "core") {
    bind<Logger>() with provider { LoggerImpl() }
    bind<Timer>() with provider { Timer() }
    bind<Controller>() with singleton {
        Controller(logger = instance(), timer = instance())
            .apply { timer.tick = { setAction(Action.TimerTick) } }
    }
}
