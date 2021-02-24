package io.pomplan.common.ui


sealed class Theme {
    abstract val colors: Colors

    object Light : Theme() {
        override val colors: Colors = Colors.Light
    }

    object Dark : Theme() {
        override val colors: Colors = Colors.Dark
    }
}

typealias ColorValue = String
sealed class Colors {
    abstract val cyan: ColorValue
    abstract val violet: ColorValue
    abstract val yellow: ColorValue
    abstract val green: ColorValue
    abstract val greyBlue: ColorValue
    abstract val red: ColorValue
    abstract val white: ColorValue
    abstract val grey: ColorValue
    abstract val blue: ColorValue
    abstract val black: ColorValue

    abstract val textPrimary: ColorValue
    abstract val background: ColorValue

    object Light : Colors() {
        override val cyan: ColorValue = "00A8FF"
        override val violet: ColorValue = "9C88FF"
        override val yellow: ColorValue = "FBC531"
        override val green: ColorValue = "4CD137"
        override val greyBlue: ColorValue = "487EB0"
        override val red: ColorValue = "E84118"
        override val white: ColorValue = "F5F6FA"
        override val grey: ColorValue = "7F8FA6"
        override val blue: ColorValue = "273C75"
        override val black: ColorValue = "353B48"

        override val textPrimary: ColorValue = black
        override val background: ColorValue = white
    }

    object Dark : Colors() {
        override val cyan: ColorValue = "0097E6"
        override val violet: ColorValue = "8C7AE6"
        override val yellow: ColorValue = "E1B12C"
        override val green: ColorValue = "44BD32"
        override val greyBlue: ColorValue = "40739E"
        override val red: ColorValue = "C23616"
        override val white: ColorValue = "DCDDE1"
        override val grey: ColorValue = "718093"
        override val blue: ColorValue = "192A56"
        override val black: ColorValue = "2F3640"

        override val textPrimary: ColorValue = white
        override val background: ColorValue = black
    }
}

sealed class TextStyle {
    abstract val font: String
    abstract val style: String
    abstract val size: Int
    abstract val letterSpace: Double

    object Heading : TextStyle() {
        override val font = "Montserrat"
        override val style = "Bold"
        override val size = 72
        override val letterSpace = 0.1
    }
    object HeadingMinimized : TextStyle() {
        override val font = "Montserrat"
        override val style = "Bold"
        override val size = 48
        override val letterSpace = 0.1
    }
}
