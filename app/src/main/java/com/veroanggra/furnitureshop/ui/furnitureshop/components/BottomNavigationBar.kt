package com.veroanggra.furnitureshop.ui.furnitureshop.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.veroanggra.furnitureshop.ui.navigation.bottomNavDestinations
import com.veroanggra.furnitureshop.ui.theme.TextDark
import com.veroanggra.furnitureshop.ui.theme.TextMuted
import com.veroanggra.reactify.gooey.GooeyRenderer
import com.veroanggra.reactify.navbar.GooeyNavIndicatorHost
import com.veroanggra.reactify.navbar.gooeyNavItem
import com.veroanggra.reactify.navbar.rememberGooeyNavIndicatorState

@Composable
fun BottomNavigationBar(
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit,
    renderer: GooeyRenderer,
    modifier: Modifier = Modifier,
) {
    val state = rememberGooeyNavIndicatorState(selectedIndex = selectedIndex)

    LaunchedEffect(selectedIndex) {
        state.select(selectedIndex)
    }

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
            bottomNavDestinations.forEachIndexed { index, destination ->
                val selected = index == selectedIndex
                Row(
                    Modifier
                        .gooeyNavItem(state, index)
                        .clip(RoundedCornerShape(24.dp))
                        .clickable { onItemSelected(index) }
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
