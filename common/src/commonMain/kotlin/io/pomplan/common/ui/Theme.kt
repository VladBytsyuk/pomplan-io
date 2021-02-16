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

typealias ColorValue = Long
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

    object Light : Colors() {
        override val cyan: ColorValue = 0xFF00A8FF
        override val violet: ColorValue = 0xFF9C88FF
        override val yellow: ColorValue = 0xFFFBC531
        override val green: ColorValue = 0xFF4CD137
        override val greyBlue: ColorValue = 0xFF487EB0
        override val red: ColorValue = 0xFFE84118
        override val white: ColorValue = 0xFFF5F6FA
        override val grey: ColorValue = 0xFF7F8FA6
        override val blue: ColorValue = 0xFF273C75
        override val black: ColorValue = 0xFF353B48

        override val textPrimary: ColorValue = black
    }

    object Dark : Colors() {
        override val cyan: ColorValue = 0xFF0097E6
        override val violet: ColorValue = 0xFF8C7AE6
        override val yellow: ColorValue = 0xFFE1B12C
        override val green: ColorValue = 0xFF44BD32
        override val greyBlue: ColorValue = 0xFF40739E
        override val red: ColorValue = 0xFFC23616
        override val white: ColorValue = 0xFFDCDDE1
        override val grey: ColorValue = 0xFF718093
        override val blue: ColorValue = 0xFF192A56
        override val black: ColorValue = 0xFF2F3640

        override val textPrimary: ColorValue = white
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
