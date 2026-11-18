package com.veroanggra.furnitureshop.data.model

data class Product(
    val id: Int,
    val name: String,
    val vendor: String,
    val price: Double,
    val category: String,
    val colorHex: Long,
    val imageRes: Int,
)
