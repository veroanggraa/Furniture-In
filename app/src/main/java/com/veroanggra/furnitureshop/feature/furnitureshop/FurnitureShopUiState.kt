package com.veroanggra.furnitureshop.feature.furnitureshop

import com.veroanggra.furnitureshop.data.model.Product

data class FurnitureShopUiState(
    val isLoading: Boolean = true,
    val categories: List<String> = emptyList(),
    val selectedCategory: String = "All",
    val products: List<Product> = emptyList(),
)
