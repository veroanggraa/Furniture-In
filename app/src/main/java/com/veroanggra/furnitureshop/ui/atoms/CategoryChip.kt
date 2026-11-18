package com.veroanggra.furnitureshop.ui.atoms

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.veroanggra.furnitureshop.ui.theme.PillInactive
import com.veroanggra.furnitureshop.ui.theme.TextMuted

@Composable
fun CategoryChip(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier
            .clip(RoundedCornerShape(50))
            .background(if (selected) Color.Transparent else PillInactive)
            .clickable(onClick = onClick)
            .padding(horizontal = 22.dp, vertical = 19.dp),
    ) {
        Text(
            text = label,
            color = if (selected) Color.White else TextMuted,
            fontSize = 15.5.sp,
            fontWeight = FontWeight.Normal,
        )
    }
}
