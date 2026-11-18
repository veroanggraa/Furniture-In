package com.veroanggra.furnitureshop.ui.atoms

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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

@Composable
fun CartPill(modifier: Modifier = Modifier, iconModifier: Modifier = Modifier) {
    Row(
        modifier
            .clip(RoundedCornerShape(50))
            .background(DarkEspresso)
            .padding(start = 18.dp, end = 16.dp, top = 14.dp, bottom = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text("Cart", color = Color.White, fontWeight = FontWeight.Normal, fontSize = 16.5.sp)
        Spacer(Modifier.width(8.dp))
        Icon(
            ImageVector.vectorResource(id = R.drawable.ic_cart),
            contentDescription = "Add to cart",
            tint = Color.Unspecified,
            modifier = iconModifier,
        )
    }
}
