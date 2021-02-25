package io.pomplan.desktop

import io.pomplan.common.domain.Pomodoro
import io.pomplan.common.domain.Pomodoro.Mode.*
import io.pomplan.common.domain.Time
import io.pomplan.common.elm.*
import io.pomplan.common.elm.Controller
import io.pomplan.common.ui.Theme
import javafx.application.Platform
import javafx.beans.property.SimpleObjectProperty
import tornadofx.*


fun main() = launch<PomPlanApp>()

class PomPlanApp : App(MainScreen::class) {
    class MainScreen() : View() {
        private val controller: PomPlanController by inject()

        val theme = Theme.Dark
        override val root = stackpane {
            backgroundView(width = 360, height = 560, theme)
            vbox {
                timerView(controller, theme)
                buttonsView(controller, theme)
            }
        }
    }
}

class PomPlanController : tornadofx.Controller() {
    private val controller: Controller by io.pomplan.common.inject()

    fun setAction(action: Action) = controller.setAction(action)

    val elapsedTime = SimpleObjectProperty("")
    val mode = SimpleObjectProperty(PRE_WORK)
    val playPauseText = SimpleObjectProperty("")

    init {
        controller.attach()
        controller.subscribeOnState("this", State::class) { state ->
            Platform.runLater { render(state) }
        }
    }

    private fun render(state: State) {
        elapsedTime.set(state.pomodoro.elapsedTime.toString())
        mode.set(state.pomodoro.mode)
        playPauseText.set(if (state.pomodoro.mode in listOf(PRE_WORK, PRE_BREAK)) "Play" else "Pause")
    }
}
