package io.pomplan.android.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun TimerScreen(
    modifier: Modifier = Modifier
) = Box(
    modifier = modifier
) {

}


@Preview(name = "Portrait TimerScreen", widthDp = 360, heightDp = 720)
@Composable
fun TimerScreen_Portrait() = TimerScreen()

@Preview(name = "Landscape TimerScreen", widthDp = 720, heightDp = 360)
@Composable
fun TimerScreen_Landscape() = TimerScreen()
