package com.ykv17.luxebloom.presentation.screens.home.state

import androidx.annotation.StringRes

data class CategoryListState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    @StringRes val errorId: Int = 0,
    val errorMessage: String = "",
    val categories: List<CategoryListItem> = emptyList()
)
