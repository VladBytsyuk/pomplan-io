package io.pomplan.android.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier
) = Box(
    modifier = modifier
) {
    Text("Settings")
}


@Preview(name = "Portrait SettingsScreen", widthDp = 360, heightDp = 720)
@Composable
private fun SettingsScreen_Portrait() = SettingsScreen()

@Preview(name = "Landscape SettingsScreen", widthDp = 720, heightDp = 360)
@Composable
private fun SettingsScreen_Landscape() = SettingsScreen()
