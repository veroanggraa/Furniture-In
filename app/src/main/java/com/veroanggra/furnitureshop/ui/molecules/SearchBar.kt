package com.veroanggra.furnitureshop.ui.molecules

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.veroanggra.furnitureshop.R
import com.veroanggra.furnitureshop.ui.atoms.CircleIconButton
import com.veroanggra.furnitureshop.ui.theme.TextMuted

@Composable
fun SearchBar(modifier: Modifier = Modifier, onFilterClick: () -> Unit = {}) {
    Row(modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Row(
            Modifier
                .weight(1f)
                .height(56.dp)
                .clip(RoundedCornerShape(28.dp))
                .background(Color.White)
                .padding(horizontal = 19.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                ImageVector.vectorResource(id = R.drawable.ic_search),
                contentDescription = null,
                tint = Color.Unspecified,
            )
            Spacer(Modifier.width(13.dp))
            Text("Search furniture", color = TextMuted, fontSize = 14.2.sp)
        }
        Spacer(Modifier.width(16.dp))
        CircleIconButton(
            icon = ImageVector.vectorResource(id = R.drawable.ic_sliders),
            contentDescription = "Filter",
            backgroundColor = Color.White,
            iconTint = Color.Unspecified,
            size = 56.dp,
            onClick = onFilterClick,
        )
    }
}
