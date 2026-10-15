package com.veroanggra.furnitureshop.ui.furnitureshop

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.veroanggra.furnitureshop.ui.furnitureshop.components.BottomNavigationBar
import com.veroanggra.furnitureshop.ui.furnitureshop.components.CategorySelector
import com.veroanggra.furnitureshop.ui.furnitureshop.components.ProductRow
import com.veroanggra.furnitureshop.ui.theme.Background
import com.veroanggra.furnitureshop.ui.theme.TextDark
import com.veroanggra.furnitureshop.ui.theme.TextMuted
import com.veroanggra.reactify.gooey.GooeyRenderers

/** Stateful entry point: wires the [FurnitureShopViewModel] to the stateless [FurnitureShopScreen]. */
@Composable
fun FurnitureShopRoute(
    modifier: Modifier = Modifier,
    viewModel: FurnitureShopViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    FurnitureShopScreen(
        uiState = uiState,
        onCategorySelected = viewModel::onCategorySelected,
        onNavItemSelected = viewModel::onNavItemSelected,
        modifier = modifier,
    )
}

@Composable
fun FurnitureShopScreen(
    uiState: FurnitureShopUiState,
    onCategorySelected: (String) -> Unit,
    onNavItemSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val renderer = remember { GooeyRenderers.auto() }

    Box(modifier.fillMaxSize().background(Background).safeDrawingPadding()) {
        Column(Modifier.fillMaxSize().padding(horizontal = 20.dp)) {
            Spacer(Modifier.height(24.dp))
            ScreenHeader()
            Spacer(Modifier.height(20.dp))
            SearchBar()
            Spacer(Modifier.height(20.dp))
            CategorySelector(
                categories = uiState.categories,
                selectedCategory = uiState.selectedCategory,
                onCategorySelected = onCategorySelected,
                renderer = renderer,
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(Modifier.height(20.dp))
            SectionHeader()
            Spacer(Modifier.height(14.dp))
            ProductRow(
                products = uiState.products,
                isLoading = uiState.isLoading,
                modifier = Modifier.weight(1f),
            )
        }

        BottomNavigationBar(
            selectedIndex = uiState.selectedNavIndex,
            onItemSelected = onNavItemSelected,
            renderer = renderer,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 24.dp),
        )
    }
}

@Composable
private fun ScreenHeader() {
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
private fun SectionHeader() {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text("Chair Collections", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = TextDark)
        Icon(Icons.Filled.Search, contentDescription = null, tint = Color.Transparent) // keeps row height stable
    }
}
