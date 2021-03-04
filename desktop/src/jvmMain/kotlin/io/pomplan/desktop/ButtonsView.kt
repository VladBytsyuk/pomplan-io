package io.pomplan.desktop

import io.pomplan.common.domain.Pomodoro.Mode.*
import io.pomplan.common.elm.Action
import io.pomplan.desktop.PomPlanStylesheet.Icons.STOP_SVG
import io.pomplan.desktop.PomPlanStylesheet.Icons.SKIP_SVG
import javafx.geometry.Pos
import javafx.scene.Parent
import tornadofx.*


fun Parent.buttonsView(controller: PomPlanController) = hbox(alignment = Pos.CENTER) {
    button(graphic = svgpath(STOP_SVG)) {
        action { controller.setAction(Action.UserClick.Button.Stop()) }
    }
    val playPauseSvg = svgpath(controller.playPauseSvg.value) {
        controller.playPauseSvg.addListener { _, _, svg -> content = svg }
    }
    button(graphic = playPauseSvg) {

        action {
            val action =
                if (controller.mode.value in listOf(PRE_WORK, PRE_BREAK)) Action.UserClick.Button.Play
                else Action.UserClick.Button.Pause
            controller.setAction(action)
        }
    }
    button(graphic = svgpath(SKIP_SVG)) {
        action { controller.setAction(Action.UserClick.Button.Skip()) }
    }
    children.addClass(PomPlanStylesheet.actionButton)
}
