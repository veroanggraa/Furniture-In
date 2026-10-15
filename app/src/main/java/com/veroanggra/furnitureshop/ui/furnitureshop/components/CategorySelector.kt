package com.veroanggra.furnitureshop.ui.furnitureshop.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.veroanggra.furnitureshop.ui.theme.Accent
import com.veroanggra.furnitureshop.ui.theme.TextMuted
import com.veroanggra.reactify.gooey.GooeyRenderer
import com.veroanggra.reactify.segment.GooeySegmentedControlHost
import com.veroanggra.reactify.segment.gooeySegmentItem
import com.veroanggra.reactify.segment.rememberGooeySegmentedControlState

@Composable
fun CategorySelector(
    categories: List<String>,
    selectedCategory: String,
    onCategorySelected: (String) -> Unit,
    renderer: GooeyRenderer,
    modifier: Modifier = Modifier,
) {
    val selectedIndex = categories.indexOf(selectedCategory).coerceAtLeast(0)
    val state = rememberGooeySegmentedControlState(selectedIndex = selectedIndex)

    LaunchedEffect(selectedIndex) {
        state.select(selectedIndex)
    }

    GooeySegmentedControlHost(
        state = state,
        color = Accent,
        renderer = renderer,
        modifier = modifier.clip(RoundedCornerShape(22.dp)),
    ) {
        Row(Modifier.padding(4.dp)) {
            categories.forEachIndexed { index, label ->
                val selected = label == selectedCategory
                Box(
                    Modifier
                        .gooeySegmentItem(state, index)
                        .clip(RoundedCornerShape(18.dp))
                        .clickable { onCategorySelected(label) }
                        .padding(horizontal = 20.dp, vertical = 10.dp),
                ) {
                    Text(
                        text = label,
                        color = if (selected) Color.White else TextMuted,
                        fontSize = 14.sp,
                        fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal,
                    )
                }
            }
        }
    }
}
