package io.pomplan.desktop

import io.pomplan.common.domain.Pomodoro
import io.pomplan.common.domain.Pomodoro.Mode.*
import io.pomplan.common.elm.State
import javafx.beans.value.ChangeListener
import javafx.beans.value.ObservableValue
import javafx.scene.Parent
import javafx.scene.layout.Pane
import javafx.scene.shape.Shape
import tornadofx.c
import tornadofx.circle
import tornadofx.stackpane


private const val SIZE = 32

fun Pane.pomodoroView(controller: PomPlanController, number: Int) = stackpane {
    circle(centerX = SIZE / 2, centerY = SIZE / 2, radius = SIZE / 2 - 4) {
        fill = null
        stroke = c(PomPlanStylesheet.theme.colors.grey)
        controller.donePomodoro.addListener { _, _, donePomodoroNumber ->
            val donePomodoro = donePomodoroNumber?.toInt() ?: return@addListener
            configure(number, donePomodoro, controller.mode.value)
        }
        controller.mode.addListener { _, _, mode ->
            mode ?: return@addListener
            configure(number, controller.donePomodoro.value, mode)
        }
    }
}

private fun Shape.configure(number: Int, donePomodoro: Int, mode: Pomodoro.Mode) {
    if (donePomodoro >= number
        || donePomodoro + 1 == number && mode in listOf(PRE_WORK, WORK)
    ) stroke = c(PomPlanStylesheet.theme.colors.red)
    if (donePomodoro >= number) fill = c(PomPlanStylesheet.theme.colors.red)
}
