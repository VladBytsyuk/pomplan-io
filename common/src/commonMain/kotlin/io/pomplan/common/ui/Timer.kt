package io.pomplan.common.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


@Composable
fun TimerView(theme: Theme, text: String) = Text(
    text = text,
    color = Color(theme.colors.textPrimary)
)
