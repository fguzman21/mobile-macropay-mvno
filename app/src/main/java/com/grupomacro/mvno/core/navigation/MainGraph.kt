package com.grupomacro.mvno.core.navigation

import android.os.Bundle
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.createGraph
import com.grupomacro.mvno.screens.home.ui.HomeScreen
import com.grupomacro.mvno.screens.portability.ui.PortabilityScreen
import com.grupomacro.mvno.screens.recharges.ui.RechargesScreen
import com.grupomacro.mvno.screens.help.ui.HelpScreen

fun buildNavigationGraph(
    navController: NavHostController
): NavGraph {
    return navController.createGraph(startDestination = Home) {
        composable<Home> {
            HomeScreen()
        }

        composable<Recharges> {
            RechargesScreen()
        }

        composable<Portability> {
            PortabilityScreen()
        }

        composable<HelpScreen.Start> {
            HelpScreen(
                launchSection = {
                    //startHelpItemActivity(HelpSectionActivity.createBundleForSection(it))
                }
            )
        }
    }
}