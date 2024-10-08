package com.grupomacro.mvno.core.navigation

import androidx.compose.foundation.layout.height
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun AppNavigationBar(
    navController: NavHostController
) {
    NavigationBar(
        modifier = Modifier.height(56.dp),
        containerColor = Color.White,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        NavigationBarEntry.navigationBarItems.forEach { navigationItem ->
            CustomNavigationBarItem(
                navigationItem = navigationItem,
                isSelected = currentDestination?.hasRoute(navigationItem.appScreen::class) ?: false,
                onClick = {
                    if (currentDestination?.hasRoute(navigationItem.appScreen::class) != true) {
                        navController.navigate(navigationItem.appScreen) {
                            popUpTo(navigationItem.appScreen)
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
            )
        }
    }
}
