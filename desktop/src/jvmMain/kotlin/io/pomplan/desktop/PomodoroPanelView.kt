package io.pomplan.desktop

import javafx.geometry.Pos
import javafx.scene.Parent
import tornadofx.hbox
import tornadofx.vbox


fun Parent.pomodoroPanelView(controller: PomPlanController) = vbox(spacing = 8, alignment = Pos.CENTER) {
    hbox(spacing = 24, alignment = Pos.CENTER) {
        pomodoroPanelItemView(controller, startNumber = 1)
        pomodoroPanelItemView(controller, startNumber = 5)
    }
    hbox(spacing = 24, alignment = Pos.CENTER) {
        pomodoroPanelItemView(controller, startNumber = 9)
        pomodoroPanelItemView(controller, startNumber = 13)
    }
}

private fun Parent.pomodoroPanelItemView(controller: PomPlanController, startNumber: Int) =
    hbox(spacing = 8, alignment = Pos.CENTER) {
        pomodoroView(controller, startNumber)
        pomodoroView(controller, startNumber + 1)
        pomodoroView(controller, startNumber + 2)
        pomodoroView(controller, startNumber + 3)
    }
