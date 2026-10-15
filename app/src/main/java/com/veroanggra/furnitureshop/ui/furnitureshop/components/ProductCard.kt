package com.veroanggra.furnitureshop.ui.furnitureshop.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Weekend
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.veroanggra.furnitureshop.data.model.Product
import com.veroanggra.furnitureshop.ui.theme.TextMuted
import java.util.Locale

@Composable
fun ProductRow(
    products: List<Product>,
    isLoading: Boolean,
    modifier: Modifier = Modifier,
) {
    Box(modifier.fillMaxSize()) {
        when {
            isLoading -> CircularProgressIndicator(Modifier.align(Alignment.Center))
            products.isEmpty() -> Text(
                text = "No products found",
                color = TextMuted,
                modifier = Modifier.align(Alignment.Center),
            )
            else -> LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(14.dp),
            ) {
                items(products, key = { it.id }) { product -> ProductCard(product) }
            }
        }
    }
}

@Composable
private fun ProductCard(product: Product) {
    Column(
        Modifier
            .width(190.dp)
            .clip(RoundedCornerShape(22.dp))
            .background(Color(product.colorHex)),
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                Icons.Filled.Weekend,
                contentDescription = product.name,
                tint = Color.White.copy(alpha = 0.9f),
                modifier = Modifier.size(72.dp),
            )
        }
        Column(Modifier.padding(14.dp)) {
            Text(product.name, color = Color.White, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
            Text(product.vendor, color = Color.White.copy(alpha = 0.8f), fontSize = 11.sp)
            Spacer(Modifier.height(8.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = String.format(Locale.US, "$%.2f", product.price),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                )
                Box(
                    Modifier
                        .size(34.dp)
                        .clip(CircleShape)
                        .background(Color.Black.copy(alpha = 0.25f)),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(Icons.Filled.ShoppingCart, contentDescription = "Add to cart", tint = Color.White, modifier = Modifier.size(18.dp))
                }
            }
        }
    }
}
