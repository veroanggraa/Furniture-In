package com.veroanggra.furnitureshop.data.repository

import com.veroanggra.furnitureshop.R
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
            colorHex = 0xFFE08E69,
            imageRes = R.drawable.pict_1_chair,
        ),
        Product(
            id = 2,
            name = "Comfortable Chair",
            vendor = "By Blade Soft",
            price = 168.00,
            category = "Chair",
            colorHex = 0xFF768FC5,
            imageRes = R.drawable.pict_2_chair,
        ),
        Product(
            id = 3,
            name = "Blush Tufted Armchair",
            vendor = "By Linen & Co",
            price = 142.00,
            category = "Chair",
            colorHex = 0xFFE3A5A9,
            imageRes = R.drawable.pict_3_chair,
        ),
        Product(
            id = 4,
            name = "Green Velvet Armchair",
            vendor = "By Mellow Home",
            price = 175.00,
            category = "Chair",
            colorHex = 0xFF8FAE6A,
            imageRes = R.drawable.pict_4_chair,
        ),
        Product(
            id = 5,
            name = "Mustard Wingback Chair",
            vendor = "By Blade Soft",
            price = 189.00,
            category = "Chair",
            colorHex = 0xFFCB8A2E,
            imageRes = R.drawable.pict_5_chair,
        ),
        Product(
            id = 6,
            name = "Scarlet Chesterfield Chair",
            vendor = "By Vintage Row",
            price = 210.00,
            category = "Chair",
            colorHex = 0xFFB33A3A,
            imageRes = R.drawable.pict_6_chair,
        ),
        Product(
            id = 7,
            name = "Lavender Accent Chair",
            vendor = "By Soft Nest",
            price = 155.00,
            category = "Chair",
            colorHex = 0xFF9B86C4,
            imageRes = R.drawable.pict_7_chair,
        ),
        Product(
            id = 8,
            name = "Golden Hour Armchair",
            vendor = "By Blade Soft",
            price = 168.00,
            category = "Chair",
            colorHex = 0xFFD99A3D,
            imageRes = R.drawable.pict_8_chair,
        ),
        Product(
            id = 9,
            name = "Sage Tufted Wingback",
            vendor = "By Linen & Co",
            price = 172.00,
            category = "Chair",
            colorHex = 0xFF8FA07E,
            imageRes = R.drawable.pict_9_chair,
        ),
        Product(
            id = 10,
            name = "Cloud Swivel Chair",
            vendor = "By Mellow Home",
            price = 198.00,
            category = "Chair",
            colorHex = 0xFFA8B0A2,
            imageRes = R.drawable.pict_10_chair,
        ),
        Product(
            id = 11,
            name = "Walnut Corduroy Chair",
            vendor = "By Vintage Row",
            price = 225.00,
            category = "Chair",
            colorHex = 0xFFB08D5B,
            imageRes = R.drawable.pict_11_chair,
        ),
        Product(
            id = 12,
            name = "Amber Wingback Chair",
            vendor = "By Blade Soft",
            price = 179.00,
            category = "Chair",
            colorHex = 0xFFD2902F,
            imageRes = R.drawable.pict_12_chair,
        ),
        Product(
            id = 13,
            name = "Sage Barrel Chair",
            vendor = "By Soft Nest",
            price = 164.00,
            category = "Chair",
            colorHex = 0xFFA9B38F,
            imageRes = R.drawable.pict_13_chair,
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
