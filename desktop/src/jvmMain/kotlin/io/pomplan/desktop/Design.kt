package io.pomplan.desktop

import io.pomplan.common.ui.Theme
import tornadofx.*

class PomPlanStylesheet : Stylesheet() {
    object Fonts {
        val montserratBold = loadFont("/fonts/montserrat_bold.ttf", 72)
    }

    companion object {
        val background by cssclass()
        val timerText by cssclass()
        val actionButton by cssclass()
    }

    private val theme: Theme = Theme.Dark
    init {
        background {
            fill = c(theme.colors.background)
        }
        timerText {
            textFill = c(theme.colors.textPrimary)
            fontSize = 72.px
            if (Fonts.montserratBold != null) fontFamily = Fonts.montserratBold.family
        }

        actionButton {
            fontSize = 20.px
        }
    }
}
