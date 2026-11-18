package com.veroanggra.furnitureshop.ui.organisms

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import com.veroanggra.furnitureshop.data.model.Product
import com.veroanggra.furnitureshop.ui.theme.TextMuted
import com.veroanggra.reactify.list.GooeyTransferState
import kotlin.math.roundToInt

@Composable
fun ProductRow(
    products: List<Product>,
    isLoading: Boolean,
    transferState: GooeyTransferState,
    modifier: Modifier = Modifier,
) {
    Box(modifier.fillMaxWidth().heightIn(min = 200.dp)) {
        when {
            isLoading -> CircularProgressIndicator(Modifier.align(Alignment.Center))
            products.isEmpty() -> Text(
                text = "No products found",
                color = TextMuted,
                modifier = Modifier.align(Alignment.Center),
            )
            else -> {
                val pagerState = rememberPagerState(pageCount = { products.size })
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.fillMaxWidth(),
                    pageSize = CardPageSize,
                    pageSpacing = 20.dp,
                ) { page ->
                    ProductCard(products[page], transferState, modifier = Modifier.fillMaxWidth())
                }
            }
        }
    }
}

// Matches the proportions measured from the Figma source (card width / available row width).
private const val CardWidthFraction = 0.756f

// One card dominates each swipe (per-screen paging), with a sliver of the next one peeking in.
private val CardPageSize = object : PageSize {
    override fun Density.calculateMainAxisPageSize(availableSpace: Int, pageSpacing: Int): Int =
        (availableSpace * CardWidthFraction).roundToInt()
}
