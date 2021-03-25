package io.pomplan.desktop

import javafx.scene.Parent
import tornadofx.hbox
import tornadofx.vbox


fun Parent.pomodoroPanelView(controller: PomPlanController) = vbox(spacing = 8) {
    hbox(spacing = 24) {
        pomodoroPanelItemView(controller, startNumber = 1)
        pomodoroPanelItemView(controller, startNumber = 5)
    }
    hbox(spacing = 24) {
        pomodoroPanelItemView(controller, startNumber = 9)
        pomodoroPanelItemView(controller, startNumber = 13)
    }
}

private fun Parent.pomodoroPanelItemView(controller: PomPlanController, startNumber: Int) =
    hbox(spacing = 8) {
        pomodoroView(controller, startNumber)
        pomodoroView(controller, startNumber + 1)
        pomodoroView(controller, startNumber + 2)
        pomodoroView(controller, startNumber + 3)
    }
