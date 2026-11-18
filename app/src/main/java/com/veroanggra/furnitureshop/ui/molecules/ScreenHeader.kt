package com.veroanggra.furnitureshop.ui.molecules

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.veroanggra.furnitureshop.R
import com.veroanggra.furnitureshop.ui.theme.DarkEspresso
import com.veroanggra.furnitureshop.ui.theme.NavHomeGold
import com.veroanggra.furnitureshop.ui.theme.TextDark
import com.veroanggra.reactify.list.GooeyTarget
import com.veroanggra.reactify.list.GooeyTransferState

@Composable
fun ScreenHeader(
    transferState: GooeyTransferState,
    modifier: Modifier = Modifier,
    onCartClick: () -> Unit = {},
) {
    Row(modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(
            text = "Find Your\nDream Furniture",
            fontSize = 32.5.sp,
            fontWeight = FontWeight.SemiBold,
            color = TextDark,
            lineHeight = 41.sp,
            letterSpacing = (-0.4).sp,
        )
        GooeyTarget(state = transferState, cornerRadius = 31.dp) {
            // The badge is a sibling of the clipped circle, not a child of it — placing it inside
            // would have it cut off by the circle's own CircleShape clip at the box's corner.
            Box(Modifier.size(62.dp)) {
                Box(
                    Modifier
                        .fillMaxSize()
                        .clip(CircleShape)
                        .background(DarkEspresso)
                        .clickable(onClick = onCartClick),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        ImageVector.vectorResource(id = R.drawable.ic_cart),
                        contentDescription = "Cart",
                        tint = Color.Unspecified,
                    )
                }
                val cartCount = transferState.transferredKeys.size
                if (cartCount > 0) {
                    Box(
                        Modifier
                            .align(Alignment.TopEnd)
                            .size(20.dp)
                            .clip(CircleShape)
                            .background(NavHomeGold),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(cartCount.toString(), color = Color.White, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}
