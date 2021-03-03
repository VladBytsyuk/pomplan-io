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

class PomPlanApp : App(MainScreen::class, PomPlanStylesheet::class) {
    init { reloadStylesheetsOnFocus() }

    class MainScreen() : View() {
        private val controller: PomPlanController by inject()

        override val root = stackpane {
            backgroundView(width = 360, height = 560)
            vbox {
                timerView(controller)
                buttonsView(controller)
            }
        }
    }
}

class PomPlanController : tornadofx.Controller() {
    private val controller: Controller by io.pomplan.common.inject()

    fun setAction(action: Action) = controller.setAction(action)

    val elapsedTimeMinutes = SimpleObjectProperty("")
    val elapsedTimeSeconds = SimpleObjectProperty("")
    val mode = SimpleObjectProperty(PRE_WORK)
    val playPauseText = SimpleObjectProperty("")

    init {
        controller.attach()
        controller.subscribeOnState("this", State::class) { state ->
            Platform.runLater { render(state) }
        }
    }

    private fun render(state: State) {
        elapsedTimeMinutes.set(state.pomodoro.elapsedTime.minuteString)
        elapsedTimeSeconds.set(state.pomodoro.elapsedTime.secondsString)
        mode.set(state.pomodoro.mode)
        playPauseText.set(if (state.pomodoro.mode in listOf(PRE_WORK, PRE_BREAK)) "Play" else "Pause")
    }
}
