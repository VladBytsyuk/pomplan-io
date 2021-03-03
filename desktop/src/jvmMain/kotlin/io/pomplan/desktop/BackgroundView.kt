package io.pomplan.desktop

import javafx.scene.Parent
import tornadofx.addClass
import tornadofx.rectangle


fun Parent.backgroundView(width: Int, height: Int) =
    rectangle(width = width, height = height) { addClass(PomPlanStylesheet.background) }
