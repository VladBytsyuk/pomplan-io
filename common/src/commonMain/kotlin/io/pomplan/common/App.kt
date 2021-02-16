package io.pomplan.common

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import io.pomplan.common.ui.Theme
import io.pomplan.common.ui.TimerView


@Composable
fun App() {

    MaterialTheme {
        TimerView(theme = Theme.Light)
    }
}
