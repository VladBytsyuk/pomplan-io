package io.pomplan.desktop

import io.pomplan.common.domain.Pomodoro.Mode.*
import io.pomplan.common.elm.Action
import io.pomplan.desktop.PomPlanStylesheet.Icons.STOP_SVG
import io.pomplan.desktop.PomPlanStylesheet.Icons.SKIP_SVG
import javafx.beans.value.ObservableValue
import javafx.geometry.Pos
import javafx.scene.Parent
import tornadofx.*


fun Parent.buttonsView(controller: PomPlanController) = hbox(spacing = 48.0, alignment = Pos.CENTER) {
    button(graphic = svgNormalIcon(STOP_SVG)) {
        action { controller.setAction(Action.UserClick.Button.Stop) }
    }
    button(graphic = svgBigIcon(controller.playPauseSvg)) {
        action {
            val action =
                if (controller.mode.value in listOf(PRE_WORK, PRE_BREAK)) Action.UserClick.Button.Play
                else Action.UserClick.Button.Pause
            controller.setAction(action)
        }
    }
    button(graphic = svgNormalIcon(SKIP_SVG)) {
        action { controller.setAction(Action.UserClick.Button.Skip()) }
    }
    children.addClass(PomPlanStylesheet.actionButton)
}

private fun Parent.svgNormalIcon(svg: String) = svgpath(svg) {
    scaleX = 2.0
    scaleY = 2.0
    fill = c(PomPlanStylesheet.theme.colors.textPrimary)
}

private fun Parent.svgBigIcon(svgObservable: ObservableValue<String>) = svgpath(svgObservable.value) {
    svgObservable.addListener { _, _, svg -> content = svg }
    scaleX = 3.0
    scaleY = 3.0
    fill = c(PomPlanStylesheet.theme.colors.textPrimary)
}
