package io.pomplan.desktop

import io.pomplan.common.ui.Theme
import tornadofx.*

class PomPlanStylesheet : Stylesheet() {
    object Fonts {
        val montserratBold = loadFont("/fonts/montserrat_bold.ttf", 72)
    }

    object Icons {
        const val STOP_SVG = "M6 6h12v12H6z"
        const val SKIP_SVG = "M6 18l8.5-6L6 6v12zM16 6v12h2V6h-2z"
        const val PLAY_SVG = "M8 5v14l11-7z"
        const val PAUSE_SVG = "M6 19h4V5H6v14zm8-14v14h4V5h-4z"
    }

    companion object {
        val theme: Theme = Theme.Dark

        val background by cssclass()
        val timerText by cssclass()
        val actionButton by cssclass()
    }

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
