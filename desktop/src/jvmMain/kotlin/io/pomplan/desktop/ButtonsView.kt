package io.pomplan.desktop

import io.pomplan.common.domain.Pomodoro.Mode.*
import io.pomplan.common.elm.Action
import javafx.scene.Parent
import tornadofx.action
import tornadofx.addClass
import tornadofx.button
import tornadofx.hbox


fun Parent.buttonsView(controller: PomPlanController) = hbox {
    button("Stop") {
        action { controller.setAction(Action.UserClick.Button.Stop()) }
    }
    button(controller.playPauseText) {
        action {
            val action =
                if (controller.mode.value in listOf(PRE_WORK, PRE_BREAK)) Action.UserClick.Button.Play
                else Action.UserClick.Button.Pause
            controller.setAction(action)
        }
    }
    button("Skip") {
        action { controller.setAction(Action.UserClick.Button.Skip()) }
    }
    children.addClass(PomPlanStylesheet.actionButton)
}
