package com.ykv17.luxebloom.presentation.screens.product.state

import androidx.annotation.StringRes

data class ProductDetailUiState (
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    @StringRes
    val errorId: Int = 0,
    val errorMessage: String = "",
    val productDetail: ProductDetail = ProductDetail("", "", "", "", "", emptyList(), "", "", "", "", "", "")
)