package io.pomplan.android

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun NavigationHost(
    navHostController: NavHostController,
    viewModel: AppViewModel = viewModel()
) {
    NavHost(navHostController, startDestination = Routes.TIMER) {
        composable(route = Routes.TIMER) {

        }
        composable(route = Routes.SETTINGS) {

        }
    }
}

private object Routes {
    const val TIMER = "timer"
    const val SETTINGS = "settings"
}