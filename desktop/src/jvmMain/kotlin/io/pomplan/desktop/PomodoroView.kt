package io.pomplan.desktop

import io.pomplan.common.domain.Pomodoro
import io.pomplan.common.domain.Pomodoro.Mode.*
import javafx.scene.layout.Pane
import javafx.scene.shape.Shape
import tornadofx.c
import tornadofx.circle
import tornadofx.stackpane


private const val SIZE = 32

fun Pane.pomodoroView(controller: PomPlanController, number: Int) = stackpane {
    circle(centerX = SIZE / 2, centerY = SIZE / 2, radius = SIZE / 2 - 4) {
        fill = null
        strokeWidth = 4.0
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
    val currentPomodoro = donePomodoro + 1
    val previous = number <= donePomodoro
    val currentWorkInProgress = mode == WORK && currentPomodoro == number
    val currentPreWork = mode == PRE_WORK && currentPomodoro == number
    val preBreak = mode == PRE_BREAK && currentPomodoro == number
    val breakInProgress = mode == BREAK && currentPomodoro == number
    stroke = if (
        previous || !preBreak && !breakInProgress && (currentWorkInProgress || currentPreWork)
    ) c(PomPlanStylesheet.theme.colors.red) else c(PomPlanStylesheet.theme.colors.grey)
    if (previous) fill = c(PomPlanStylesheet.theme.colors.red)
}
