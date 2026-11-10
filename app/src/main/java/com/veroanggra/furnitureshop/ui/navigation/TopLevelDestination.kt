package com.veroanggra.furnitureshop.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation3.runtime.NavKey

sealed class TopLevelDestination(val icon: ImageVector, val label: String) : NavKey {
    data object Home : TopLevelDestination(Icons.Filled.Home, "Home")
    data object Search : TopLevelDestination(Icons.Filled.Search, "Search")
    data object Wishlist : TopLevelDestination(Icons.Filled.FavoriteBorder, "Wishlist")
    data object Profile : TopLevelDestination(Icons.Filled.Person, "Profile")
}

val bottomNavDestinations = listOf(
    TopLevelDestination.Home,
    TopLevelDestination.Search,
    TopLevelDestination.Wishlist,
    TopLevelDestination.Profile,
)
