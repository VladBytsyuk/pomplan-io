package io.pomplan.desktop

import io.pomplan.common.ui.Theme
import javafx.geometry.Pos
import javafx.scene.Parent
import javafx.scene.shape.*
import tornadofx.*


fun Parent.timerView(controller: PomPlanController) = stackpane {
    timerProgress(controller)
    timerText(controller)
}


private const val SIZE = 256

private fun Parent.timerText(controller: PomPlanController) = hbox(spacing = 8, alignment = Pos.CENTER) {
    label(controller.elapsedTimeMinutes)
    label(text = ":")
    label(controller.elapsedTimeSeconds)
    children.addClass(PomPlanStylesheet.timerText)
}

private fun Parent.timerProgress(controller: PomPlanController) = stackpane {
    group {
        rectangle(x = 0.0, y = 0.0, width = SIZE, height = SIZE) {
            fill = c(PomPlanStylesheet.theme.colors.background)
        }
        arc(centerX = SIZE / 2, centerY = SIZE / 2, radiusX = SIZE / 2, radiusY = SIZE / 2, startAngle = 90.0) {
            type = ArcType.ROUND
            fillProperty().bind(controller.progressArcColor)
            lengthProperty().bind(controller.angle)
        }
        circle(centerX = SIZE / 2, centerY = SIZE / 2, radius = SIZE / 2 - 4) {
            fill = c(PomPlanStylesheet.theme.colors.background)
        }
    }
}
