package com.veroanggra.furnitureshop

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material.icons.filled.Weekend
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.veroanggra.reactify.gooey.GooeyRenderers
import com.veroanggra.reactify.navbar.GooeyNavIndicatorHost
import com.veroanggra.reactify.navbar.gooeyNavItem
import com.veroanggra.reactify.navbar.rememberGooeyNavIndicatorState
import com.veroanggra.reactify.segment.GooeySegmentedControlHost
import com.veroanggra.reactify.segment.gooeySegmentItem
import com.veroanggra.reactify.segment.rememberGooeySegmentedControlState

private val Background = Color(0xFFF5F4F2)
private val Accent = Color(0xFFCB8A2E)
private val TextDark = Color(0xFF1C1C1C)
private val TextMuted = Color(0xFF8A8A8A)
private val CardOrange = Color(0xFFE79A5C)
private val CardBlue = Color(0xFF34538A)

private data class Product(val name: String, val vendor: String, val price: String, val color: Color)

private val chairCollections = listOf(
    Product("Orange Armchair Chair", "By Blade Soft", "$168.00", CardOrange),
    Product("Comfort Wood Chair", "By Blade Soft", "$168.00", CardBlue),
)

private val categories = listOf("All", "Chair", "Table", "Sofa")
private data class NavDestination(val icon: androidx.compose.ui.graphics.vector.ImageVector, val label: String)

private val navDestinations = listOf(
    NavDestination(Icons.Filled.Home, "Home"),
    NavDestination(Icons.Filled.Search, "Search"),
    NavDestination(Icons.Filled.FavoriteBorder, "Wishlist"),
    NavDestination(Icons.Filled.Person, "Profile"),
)

/** Page 2 of the furniture-shop mockup: category filter + bottom nav, both driven by Reactify. */
@Composable
fun FurnitureShopScreen() {
    val renderer = remember { GooeyRenderers.auto() }

    Box(Modifier.fillMaxSize().background(Background)) {
        Column(Modifier.fillMaxSize().padding(horizontal = 20.dp)) {
            Spacer(Modifier.height(24.dp))
            Header()
            Spacer(Modifier.height(20.dp))
            SearchBar()
            Spacer(Modifier.height(20.dp))
            CategoryRow(renderer = renderer)
            Spacer(Modifier.height(20.dp))
            SectionHeader()
            Spacer(Modifier.height(14.dp))
            ProductRow(Modifier.weight(1f))
        }

        BottomNavBar(
            renderer = renderer,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 24.dp),
        )
    }
}

@Composable
private fun Header() {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(
            text = "Find Your\nDream Furniture",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = TextDark,
            lineHeight = 32.sp,
        )
        Box(
            Modifier
                .size(44.dp)
                .clip(CircleShape)
                .background(Color(0xFFDDD9D4)),
        )
    }
}

@Composable
private fun SearchBar() {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Row(
            Modifier
                .weight(1f)
                .height(52.dp)
                .clip(RoundedCornerShape(26.dp))
                .background(Color.White)
                .padding(horizontal = 18.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(Icons.Filled.Search, contentDescription = null, tint = TextMuted)
            Spacer(Modifier.width(10.dp))
            Text("Search furniture", color = TextMuted, fontSize = 14.sp)
        }
        Spacer(Modifier.width(10.dp))
        Box(
            Modifier
                .size(52.dp)
                .clip(CircleShape)
                .background(Color.White),
            contentAlignment = Alignment.Center,
        ) {
            Icon(Icons.Filled.Tune, contentDescription = "Filter", tint = TextDark)
        }
    }
}

@Composable
private fun CategoryRow(renderer: com.veroanggra.reactify.gooey.GooeyRenderer) {
    val state = rememberGooeySegmentedControlState(selectedIndex = 1)

    GooeySegmentedControlHost(
        state = state,
        color = Accent,
        renderer = renderer,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(22.dp)),
    ) {
        Row(Modifier.padding(4.dp)) {
            categories.forEachIndexed { index, label ->
                val selected = state.selectedIndex == index
                Box(
                    Modifier
                        .gooeySegmentItem(state, index)
                        .clip(RoundedCornerShape(18.dp))
                        .clickable { state.select(index) }
                        .padding(horizontal = 20.dp, vertical = 10.dp),
                ) {
                    Text(
                        text = label,
                        color = if (selected) Color.White else TextMuted,
                        fontSize = 14.sp,
                        fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal,
                    )
                }
            }
        }
    }
}

@Composable
private fun SectionHeader() {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text("Chair Collections", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = TextDark)
        Icon(Icons.Filled.Search, contentDescription = null, tint = Color.Transparent) // keeps row height stable
    }
}

@Composable
private fun ProductRow(modifier: Modifier = Modifier) {
    Row(
        modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        chairCollections.forEach { product -> ProductCard(product) }
    }
}

@Composable
private fun ProductCard(product: Product) {
    Column(
        Modifier
            .width(190.dp)
            .clip(RoundedCornerShape(22.dp))
            .background(product.color),
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                Icons.Filled.Weekend,
                contentDescription = product.name,
                tint = Color.White.copy(alpha = 0.9f),
                modifier = Modifier.size(72.dp),
            )
        }
        Column(Modifier.padding(14.dp)) {
            Text(product.name, color = Color.White, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
            Text(product.vendor, color = Color.White.copy(alpha = 0.8f), fontSize = 11.sp)
            Spacer(Modifier.height(8.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Text(product.price, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                Box(
                    Modifier
                        .size(34.dp)
                        .clip(CircleShape)
                        .background(Color.Black.copy(alpha = 0.25f)),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(Icons.Filled.ShoppingCart, contentDescription = "Add to cart", tint = Color.White, modifier = Modifier.size(18.dp))
                }
            }
        }
    }
}

@Composable
private fun BottomNavBar(renderer: com.veroanggra.reactify.gooey.GooeyRenderer, modifier: Modifier = Modifier) {
    val state = rememberGooeyNavIndicatorState(selectedIndex = 0)

    GooeyNavIndicatorHost(
        state = state,
        color = TextDark,
        renderer = renderer,
        modifier = modifier
            .clip(RoundedCornerShape(30.dp))
            .background(Color.White)
            .border(1.dp, Color(0xFFE6E3DF), RoundedCornerShape(30.dp)),
    ) {
        Row(Modifier.padding(6.dp), verticalAlignment = Alignment.CenterVertically) {
            navDestinations.forEachIndexed { index, destination ->
                val selected = state.selectedIndex == index
                Row(
                    Modifier
                        .gooeyNavItem(state, index)
                        .clip(RoundedCornerShape(24.dp))
                        .clickable { state.select(index) }
                        .padding(horizontal = if (selected) 18.dp else 14.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(destination.icon, contentDescription = destination.label, tint = if (selected) Color.White else TextMuted)
                    if (selected) {
                        Spacer(Modifier.width(8.dp))
                        Text(destination.label, color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Medium)
                    }
                }
            }
        }
    }
}
