package io.pomplan.desktop

import io.pomplan.common.ui.Theme
import javafx.scene.Parent
import tornadofx.rectangle
import javafx.scene.paint.Color


fun Parent.backgroundView(width: Int, height: Int, theme: Theme) =
    rectangle(width = width, height = height) { fill = Color.web(theme.colors.background) }
