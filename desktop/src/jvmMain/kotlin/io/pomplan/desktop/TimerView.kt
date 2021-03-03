package io.pomplan.desktop

import javafx.geometry.Pos
import javafx.scene.Parent
import tornadofx.*


fun Parent.timerView(controller: PomPlanController) = hbox(spacing = 8, alignment = Pos.CENTER) {
    label(controller.elapsedTimeMinutes)
    label(text = ":")
    label(controller.elapsedTimeSeconds)
    children.addClass(PomPlanStylesheet.timerText)
}
