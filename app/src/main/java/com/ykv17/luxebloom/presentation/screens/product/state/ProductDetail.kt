package com.ykv17.luxebloom.presentation.screens.product.state

data class ProductDetail(
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
    val title: String,
    val actualPrice: String
)
