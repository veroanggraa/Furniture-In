package com.veroanggra.furnitureshop.ui.organisms

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.veroanggra.furnitureshop.ui.navigation.bottomNavDestinations
import com.veroanggra.furnitureshop.ui.theme.NavBarBackground
import com.veroanggra.furnitureshop.ui.theme.NavCircleBackground
import com.veroanggra.furnitureshop.ui.theme.NavHomeGold
import com.veroanggra.furnitureshop.ui.theme.TextDark
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
        color = Color.White,
        renderer = renderer,
        modifier = modifier
            .clip(RoundedCornerShape(40.5.dp))
            .background(NavBarBackground),
    ) {
        Row(
            Modifier.padding(6.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            bottomNavDestinations.forEachIndexed { index, destination ->
                val selected = index == selectedIndex
                val icon = ImageVector.vectorResource(id = destination.iconRes)
                if (selected) {
                    Row(
                        Modifier
                            .gooeyNavItem(state, index)
                            .clip(RoundedCornerShape(32.dp))
                            .clickable { onItemSelected(index) }
                            .padding(start = 2.dp, top = 2.dp, end = 17.dp, bottom = 2.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Box(
                            Modifier.size(60.dp).clip(CircleShape).background(NavHomeGold),
                            contentAlignment = Alignment.Center,
                        ) {
                            Icon(icon, contentDescription = destination.label, tint = Color.Unspecified)
                        }
                        Spacer(Modifier.width(12.dp))
                        Text(destination.label, color = TextDark, fontSize = 14.5.sp, fontWeight = FontWeight.Medium)
                    }
                } else {
                    Box(
                        Modifier
                            .gooeyNavItem(state, index)
                            .size(64.dp)
                            .clip(CircleShape)
                            .background(NavCircleBackground)
                            .clickable { onItemSelected(index) },
                        contentAlignment = Alignment.Center,
                    ) {
                        Icon(icon, contentDescription = destination.label, tint = Color.Unspecified)
                    }
                }
            }
        }
    }
}
