package com.ykv17.luxebloom.domain.entity

data class ProductsEntity(
    val limit: Int,
    val products: List<ProductEntity>,
    val skip: Int,
    val total: Int
)

