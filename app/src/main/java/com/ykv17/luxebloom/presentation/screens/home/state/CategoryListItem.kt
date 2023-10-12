package com.ykv17.luxebloom.presentation.screens.home.state

sealed interface CategoryListItem {
    data class CategoryItem(
        val name: String,
        val searchValue: String,
        var isSelected: Boolean = false
    ) : CategoryListItem
}