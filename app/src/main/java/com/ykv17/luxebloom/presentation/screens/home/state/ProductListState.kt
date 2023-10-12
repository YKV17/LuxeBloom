package com.ykv17.luxebloom.presentation.screens.home.state

import androidx.annotation.StringRes

data class ProductListState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    @StringRes val errorId: Int = 0,
    val errorMessage: String = "",
    val products: List<ProductListItem> = emptyList()
)
