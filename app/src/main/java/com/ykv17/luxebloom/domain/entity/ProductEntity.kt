package com.ykv17.luxebloom.domain.entity

data class ProductEntity(
    val brand: String,
    val category: String,
    val description: String,
    val discountPercentage: String,
    val id: String,
    val images: List<String>,
    val price: String,
    val rating: String,
    val stock: String,
    val thumbnail: String,
    val title: String
)