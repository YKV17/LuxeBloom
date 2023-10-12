package com.ykv17.luxebloom.data.remote.dto

data class ProductsDto(
    val limit: Int?,
    val products: List<ProductDto>?,
    val skip: Int?,
    val total: Int?
)

