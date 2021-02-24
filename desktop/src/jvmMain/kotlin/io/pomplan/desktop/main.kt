package io.pomplan.desktop

import io.pomplan.common.ui.Theme
import javafx.scene.paint.Color
import tornadofx.*


fun main() = launch<PomPlanApp>()

class PomPlanApp : App(FullView::class) {
    class FullView : View() {
        val theme = Theme.Dark
        override val root = stackpane {
            backgroundView(width = 360, height = 560, theme)
            label("Hello!") {
                style { textFill = Color.web(theme.colors.textPrimary) }
            }
        }
    }
}
