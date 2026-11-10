package com.veroanggra.furnitureshop.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.veroanggra.furnitureshop.ui.theme.TextDark

/** Temporary content for destinations that don't have a real screen yet. */
@Composable
fun PlaceholderScreen(title: String, modifier: Modifier = Modifier) {
    Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(title, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = TextDark)
    }
}
