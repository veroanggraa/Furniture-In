package com.veroanggra.furnitureshop.ui.atoms

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import java.util.Locale

@Composable
fun PriceText(price: Double, modifier: Modifier = Modifier, color: Color = Color.White) {
    Text(
        text = String.format(Locale.US, "$%.2f", price),
        color = color,
        fontWeight = FontWeight.SemiBold,
        fontSize = 21.5.sp,
        modifier = modifier,
    )
}
