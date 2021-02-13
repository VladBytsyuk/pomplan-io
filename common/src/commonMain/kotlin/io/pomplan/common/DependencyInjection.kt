package io.pomplan.common

import io.pomplan.common.elm.*
import org.kodein.di.*


val container = DI.lazy { import(coreModule) }

private val coreModule = DI.Module("core") {
    bind<Logger>() with factory { LoggerImpl() }
    bind<Elm.Controller<State, Action, Effect>>() with singleton {
        Controller(logger = instance(), pomodoroTimer = instance())
    }
    bind<PomodoroTimer>() with factory { PomodoroTimer(controller = instance()) }
}

inline fun <reified T> inject(): T = container.direct.instance()
