package io.pomplan.android

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*
import io.pomplan.android.routing.Destination
import io.pomplan.android.screen.SettingsScreen
import io.pomplan.android.screen.TimerScreen
import io.pomplan.android.view.BottomBar


@Composable
fun Router(
    navHostController: NavHostController,
    viewModel: AppViewModel = viewModel()
) = Scaffold(
    bottomBar = { BottomBar(navHostController) }
) {
    NavHost(navHostController, startDestination = Destination.TIMER.route) {
        composable(route = Destination.TIMER.route) { TimerScreen() }
        composable(route = Destination.SETTINGS.route) { SettingsScreen() }
    }
}
