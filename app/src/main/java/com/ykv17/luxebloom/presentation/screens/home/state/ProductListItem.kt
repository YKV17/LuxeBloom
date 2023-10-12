package com.ykv17.luxebloom.presentation.screens.home.state

sealed interface ProductListItem {
    data class ProductItem(
        val id: String,
        val name: String,
        val price: String,
        val actualPrice: String,
        val image: String,
        val rating: String,
        val brand: String,
        val discountPercentage: String
    ) : ProductListItem
}