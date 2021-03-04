package io.pomplan.desktop

import io.pomplan.common.domain.Pomodoro
import io.pomplan.common.domain.Pomodoro.Mode.*
import io.pomplan.common.domain.Time
import io.pomplan.common.elm.*
import io.pomplan.common.elm.Controller
import javafx.application.Platform
import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleObjectProperty
import tornadofx.*


fun main() = launch<PomPlanApp>()

class PomPlanApp : App(MainScreen::class, PomPlanStylesheet::class) {
    init { reloadStylesheetsOnFocus() }

    class MainScreen : View() {
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
    val angle = SimpleDoubleProperty(0.0)
    val progressArcColor = SimpleObjectProperty(c("FFFFFF"))


    init {
        controller.attach()
        controller.subscribeOnState("this", State::class) { state ->
            Platform.runLater { render(state) }
        }
    }

    private fun render(state: State) {
        val mode = state.pomodoro.mode
        val elapsedTime = state.pomodoro.elapsedTime
        val goalTime = state.pomodoro.goalTime

        renderTimer(mode, elapsedTime, goalTime)
        renderButtons(mode)
    }

    private fun renderTimer(mode: Pomodoro.Mode, elapsedTime: Time, goalTime: Time) {
        renderTimerText(elapsedTime)
        renderTimerArc(mode, elapsedTime, goalTime)
    }

    private fun renderTimerText(elapsedTime: Time) {
        this.elapsedTimeMinutes.set(elapsedTime.minuteString)
        this.elapsedTimeSeconds.set(elapsedTime.secondsString)
    }

    private fun renderTimerArc(mode: Pomodoro.Mode, elapsedTime: Time, goalTime: Time) {
        val workArcAngle = 360.0 * elapsedTime.milliseconds / goalTime.milliseconds
        val arcAngle = when (mode) {
            PRE_WORK, WORK -> workArcAngle
            PRE_BREAK, BREAK -> 360 - workArcAngle
        }
        this.angle.set(arcAngle)

        val arcColor = when (mode) {
            PRE_WORK, WORK -> c(PomPlanStylesheet.theme.colors.red)
            PRE_BREAK, BREAK -> c(PomPlanStylesheet.theme.colors.grey)
        }
        this.progressArcColor.set(arcColor)
    }

    private fun renderButtons(mode: Pomodoro.Mode) {
        this.mode.set(mode)
        this.playPauseText.set(if (mode in listOf(PRE_WORK, PRE_BREAK)) "Play" else "Pause")
    }
}
