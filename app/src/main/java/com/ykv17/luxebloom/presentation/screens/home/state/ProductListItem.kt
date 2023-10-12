package com.ykv17.luxebloom.presentation.screens.home.state

sealed interface ProductListItem{
    data class ProductItem(
        val name: String,
        val price: String,
        val image: String
    ): ProductListItem
}