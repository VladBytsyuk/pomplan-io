package io.pomplan.android.routing

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import io.pomplan.android.R


enum class Destination(
    val route: String,
    @DrawableRes val icon: Int,
    @StringRes val title: Int,
) {
    TIMER(
        route = "timer",
        icon = R.drawable.ic_timer,
        title = R.string.bottom_bar_timer
    ),
    SETTINGS(
        route = "settings",
        icon = R.drawable.ic_settings,
        title = R.string.bottom_bar_settings
    ),
}
