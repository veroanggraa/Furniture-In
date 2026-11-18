package com.veroanggra.furnitureshop.ui.navigation

import androidx.annotation.DrawableRes
import androidx.navigation3.runtime.NavKey
import com.veroanggra.furnitureshop.R

sealed class TopLevelDestination(@DrawableRes val iconRes: Int, val label: String) : NavKey {
    data object Home : TopLevelDestination(R.drawable.ic_nav_home, "Home")
    data object Search : TopLevelDestination(R.drawable.ic_nav_search, "Search")
    data object Wishlist : TopLevelDestination(R.drawable.ic_nav_heart, "Wishlist")
    data object Profile : TopLevelDestination(R.drawable.ic_nav_profile, "Profile")
}

val bottomNavDestinations = listOf(
    TopLevelDestination.Home,
    TopLevelDestination.Search,
    TopLevelDestination.Wishlist,
    TopLevelDestination.Profile,
)
