package io.pomplan.desktop

import tornadofx.*


fun main() = launch<PomPlanApp>()

class PomPlanApp : App(FullView::class) {
    class FullView : View() {
        override val root = vbox {
            text("Hello!")
        }
    }
}
