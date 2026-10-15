package com.veroanggra.furnitureshop.data.repository

import com.veroanggra.furnitureshop.data.model.Product

interface ProductRepository {
    suspend fun getCategories(): List<String>
    suspend fun getProducts(category: String): List<Product>
}
