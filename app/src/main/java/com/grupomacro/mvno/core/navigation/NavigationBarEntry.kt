package com.grupomacro.mvno.core.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.grupomacro.mvno.R

data class NavigationBarEntry(
    val appScreen: AppScreen,
    @DrawableRes val icon: Int,
    @StringRes val label: Int,
) {
    companion object {
        val navigationBarItems = listOf(
            NavigationBarEntry(
                Home,
                R.drawable.ic_navigation_house,
                R.string.main_menu_name_home,
            ),
            NavigationBarEntry(
                Recharges,
                R.drawable.ic_navigation_recharges,
                R.string.main_menu_name_recharges,
            ),

            NavigationBarEntry(
                Portability,
                R.drawable.ic_navigation_portability,
                R.string.main_menu_name_portability,
            ),
            NavigationBarEntry(
                HelpScreen.Start,
                R.drawable.ic_navigation_help,
                R.string.main_menu_name_help,
            )
        )
    }
}
