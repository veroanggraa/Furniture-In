package com.veroanggra.furnitureshop.ui.molecules

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.veroanggra.furnitureshop.ui.atoms.CategoryChip
import com.veroanggra.furnitureshop.ui.theme.Accent
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
        Row(Modifier.padding(4.dp), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            categories.forEachIndexed { index, label ->
                CategoryChip(
                    label = label,
                    selected = label == selectedCategory,
                    onClick = { onCategorySelected(label) },
                    modifier = Modifier.gooeySegmentItem(state, index),
                )
            }
        }
    }
}
