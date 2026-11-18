package com.veroanggra.furnitureshop.data.repository

import com.veroanggra.furnitureshop.data.model.Product
import javax.inject.Inject
import kotlinx.coroutines.delay

class FakeProductRepository @Inject constructor() : ProductRepository {

    private val categories = listOf("All", "Chair", "Table", "Sofa")

    private val products = listOf(
        Product(
            id = 1,
            name = "Orange Armchair Chair",
            vendor = "By Blade Soft",
            price = 168.00,
            category = "Chair",
            colorHex = 0xFFE79A5C,
        ),
        Product(
            id = 2,
            name = "Comfort Wood Chair",
            vendor = "By Blade Soft",
            price = 168.00,
            category = "Chair",
            colorHex = 0xFF34538A,
        ),
    )

    override suspend fun getCategories(): List<String> {
        delay(CATEGORY_LOAD_DELAY_MS)
        return categories
    }

    override suspend fun getProducts(category: String): List<Product> {
        delay(PRODUCT_LOAD_DELAY_MS)
        return if (category == "All") products else products.filter { it.category == category }
    }

    private companion object {
        const val CATEGORY_LOAD_DELAY_MS = 150L
        const val PRODUCT_LOAD_DELAY_MS = 300L
    }
}
