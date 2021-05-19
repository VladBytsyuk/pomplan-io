package io.pomplan.android

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.lifecycle.viewmodel.compose.viewModel
import io.pomplan.android.screen.SettingsScreen
import io.pomplan.android.screen.TimerScreen


@Composable
fun Router(
    navHostController: NavHostController,
    viewModel: AppViewModel = viewModel()
) = Surface() {
    NavHost(navHostController, startDestination = Routes.TIMER) {
        composable(route = Routes.TIMER) { TimerScreen() }
        composable(route = Routes.SETTINGS) { SettingsScreen() }
    }
}

private object Routes {
    const val TIMER = "timer"
    const val SETTINGS = "settings"
}