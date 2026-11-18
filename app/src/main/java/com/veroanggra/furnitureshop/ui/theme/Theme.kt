package com.veroanggra.furnitureshop.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val FurnitureShopColorScheme = lightColorScheme(
    primary = Accent,
    background = Background,
    onBackground = TextDark,
    surface = Color.White,
    onSurface = TextDark,
)

@Composable
fun FurnitureShopTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = FurnitureShopColorScheme,
        typography = FurnitureShopTypography,
        content = content,
    )
}
