package io.pomplan.desktop

import io.pomplan.common.ui.Theme
import javafx.scene.Parent
import javafx.scene.control.Label
import javafx.scene.paint.Color
import tornadofx.*


fun Parent.timerView(controller: PomPlanController, theme: Theme): Label =
    label(controller.elapsedTime) {
        style { textFill = Color.web(theme.colors.textPrimary) }
    }
