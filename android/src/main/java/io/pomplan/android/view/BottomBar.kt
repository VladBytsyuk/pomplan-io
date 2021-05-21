package io.pomplan.android.view

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.KEY_ROUTE
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import io.pomplan.android.routing.Destination


@Composable
fun BottomBar(navController: NavHostController) = BottomNavigation {
    Destination.values().forEach { destination ->
        BottomNavigationItem(
            icon = { BottomBarItemIcon(destination) },
            label = { BottomBarItemTitle(destination) },
            selected = navController.currentRoute() == destination.route,
            onClick = {
                navController.navigate(destination.route) { launchSingleTop = true }
            }
        )
    }
}

@Composable
private fun BottomBarItemIcon(destination: Destination) = Icon(
    painter = painterResource(id = destination.icon),
    contentDescription = stringResource(id = destination.title)
)

@Composable
private fun BottomBarItemTitle(destination: Destination) = Text(
    text = stringResource(id = destination.title)
)

@Composable
private fun NavHostController.currentRoute(): String? {
    val navBackStackEntry by currentBackStackEntryAsState()
    return navBackStackEntry?.arguments?.getString(KEY_ROUTE)
}



@Preview(name = "Portrait BottomBar", widthDp = 360, heightDp = 720)
@Composable
private fun BottomBar_Portrait() = BottomBar(rememberNavController())

@Preview(name = "Landscape BottomBar", widthDp = 720, heightDp = 360)
@Composable
private fun BottomBar_Landscape() = BottomBar(rememberNavController())




