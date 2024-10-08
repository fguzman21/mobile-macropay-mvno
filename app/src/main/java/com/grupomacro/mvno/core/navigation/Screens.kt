package com.grupomacro.mvno.core.navigation

import kotlinx.serialization.Serializable

sealed interface AppScreen

@Serializable
data object Home : AppScreen

@Serializable
data object Recharges : AppScreen

@Serializable
data object Portability : AppScreen


sealed interface HelpScreen : AppScreen {

    @Serializable
    data object Start : HelpScreen

}
