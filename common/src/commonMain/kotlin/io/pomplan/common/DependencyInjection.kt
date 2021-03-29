package io.pomplan.common

import io.pomplan.common.domain.Settings
import io.pomplan.common.elm.*
import org.kodein.di.*


val kodein get() = container.direct
inline fun <reified T> inject(): Lazy<T> = lazy { kodein.instance() }

private val container = DI.lazy { import(coreModule) }

private val coreModule = DI.Module(name = "core") {
    bind<Logger>() with provider { LoggerImpl() }
    bind<Repository<Settings>>() with provider { SettingsRepositoryImpl() }
    bind<Timer>() with provider { Timer() }
    bind<Controller>() with singleton {
        Controller(logger = instance(), timer = instance())
            .apply { timer.tickAction = { setAction(Action.TimerTick()) } }
    }
}
