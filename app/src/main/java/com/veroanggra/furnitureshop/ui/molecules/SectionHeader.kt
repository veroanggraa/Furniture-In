package com.veroanggra.furnitureshop.ui.molecules

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.veroanggra.furnitureshop.R
import com.veroanggra.furnitureshop.ui.theme.TextDark

@Composable
fun SectionHeader(title: String, modifier: Modifier = Modifier) {
    Row(modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        Text(
            title,
            fontSize = 20.5.sp,
            fontWeight = FontWeight.SemiBold,
            color = TextDark,
            letterSpacing = (-0.2).sp,
        )
        Icon(
            ImageVector.vectorResource(id = R.drawable.ic_arrow_right),
            contentDescription = "See all",
            tint = Color.Unspecified,
        )
    }
}
