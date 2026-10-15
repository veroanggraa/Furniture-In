package com.veroanggra.furnitureshop.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavDestination(val icon: ImageVector, val label: String)

val bottomNavDestinations = listOf(
    BottomNavDestination(Icons.Filled.Home, "Home"),
    BottomNavDestination(Icons.Filled.Search, "Search"),
    BottomNavDestination(Icons.Filled.FavoriteBorder, "Wishlist"),
    BottomNavDestination(Icons.Filled.Person, "Profile"),
)
