package com.ykv17.luxebloom.data.remote.dto

data class ProductDto(
    val brand: String?,
    val category: String?,
    val description: String?,
    val discountPercentage: Double?,
    val id: Int?,
    val images: List<String>?,
    val price: Int?,
    val rating: Double?,
    val stock: Int?,
    val thumbnail: String?,
    val title: String?
)