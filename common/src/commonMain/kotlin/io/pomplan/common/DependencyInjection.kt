package io.pomplan.common

import io.pomplan.common.elm.*
import org.kodein.di.*


val kodein get() = container.direct
inline fun <reified T> inject(): T = kodein.instance()

private val container = DI.lazy { import(coreModule) }

private val coreModule = DI.Module(name = "core") {
    bind<Logger>() with provider { LoggerImpl() }
    bind<Timer>() with provider { Timer() }
    bind<Controller>() with singleton {
        Controller(logger = instance(), timer = instance())
            .apply { timer.tickAction = { setAction(Action.TimerTick()) } }
    }
}
