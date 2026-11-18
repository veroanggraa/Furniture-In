package com.veroanggra.furnitureshop.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.veroanggra.furnitureshop.ui.common.PlaceholderScreen
import com.veroanggra.furnitureshop.feature.furnitureshop.FurnitureShopRoute
import com.veroanggra.furnitureshop.ui.organisms.BottomNavigationBar
import com.veroanggra.furnitureshop.ui.theme.Background
import com.veroanggra.reactify.gooey.GooeyRenderers

/** App-level entry point: wires the bottom nav bar to a [TopLevelBackStack] rendered by [NavDisplay]. */
@Composable
fun FurnitureShopApp(modifier: Modifier = Modifier) {
    val topLevelBackStack = remember { TopLevelBackStack<TopLevelDestination>(TopLevelDestination.Home) }
    val renderer = remember { GooeyRenderers.auto() }

    Box(modifier.fillMaxSize().background(Background).safeDrawingPadding()) {
        NavDisplay(
            backStack = topLevelBackStack.backStack,
            onBack = { topLevelBackStack.removeLast() },
            entryProvider = entryProvider {
                entry<TopLevelDestination.Home> { FurnitureShopRoute() }
                entry<TopLevelDestination.Search> { PlaceholderScreen("Search") }
                entry<TopLevelDestination.Wishlist> { PlaceholderScreen("Wishlist") }
                entry<TopLevelDestination.Profile> { PlaceholderScreen("Profile") }
            },
            modifier = Modifier.fillMaxSize(),
        )

        BottomNavigationBar(
            selectedIndex = bottomNavDestinations.indexOf(topLevelBackStack.topLevelKey),
            onItemSelected = { index -> topLevelBackStack.addTopLevel(bottomNavDestinations[index]) },
            renderer = renderer,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 24.dp),
        )
    }
}
