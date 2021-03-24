package io.pomplan.desktop

import javafx.beans.value.ChangeListener
import javafx.beans.value.ObservableValue
import javafx.scene.Parent
import tornadofx.c
import tornadofx.circle
import tornadofx.stackpane


private const val SIZE = 32

fun Parent.pomodoroView(controller: PomPlanController, number: Int) = stackpane {
    circle(centerX = SIZE / 2, centerY = SIZE / 2, radius = SIZE / 2 - 4) {
        val listener = numberChangeListener { donePomodoro ->
            stroke = when {
                donePomodoro < number -> c(PomPlanStylesheet.theme.colors.red)
                else -> c(PomPlanStylesheet.theme.colors.grey)
            }
        }
        controller.donePomodoro.addListener(listener)
    }
}

private fun numberChangeListener(block: (Int) -> Unit) = object : ChangeListener<Number> {
    override fun changed(observable: ObservableValue<out Number>?, oldValue: Number?, newValue: Number?) {
        val intValue = newValue?.toInt() ?: return
        block(intValue)
    }
}
