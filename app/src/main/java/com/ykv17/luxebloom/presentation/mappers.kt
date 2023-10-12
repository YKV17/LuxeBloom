package com.ykv17.luxebloom.presentation

import com.ykv17.luxebloom.domain.entity.CategoriesEntity
import com.ykv17.luxebloom.domain.entity.ProductEntity
import com.ykv17.luxebloom.domain.entity.ProductsEntity
import com.ykv17.luxebloom.presentation.screens.home.state.CategoryListItem
import com.ykv17.luxebloom.presentation.screens.home.state.ProductListItem
import java.util.Locale

fun CategoriesEntity.toCategories(): List<CategoryListItem> {
    return map {
        CategoryListItem.CategoryItem(name = it.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(
                Locale.ROOT
            ) else it.toString()
        }, searchValue = it)
    }
}

fun ProductsEntity.toProducts(): List<ProductListItem> {
    return products.map {
        it.toProduct()
    }
}

fun ProductEntity.toProduct(): ProductListItem {
    return ProductListItem.ProductItem(
        id = id,
        name = title,
        image = thumbnail,
        price = "₹$price",
        rating = rating,
        brand = brand,
        discountPercentage = discountPercentage,
        actualPrice = "₹${getActualPrice(price.toDouble(), discountPercentage.toDouble())}"
    )
}

private fun getActualPrice(amount: Double, discountPercentage: Double): String{
    val decimalDiscount = discountPercentage / 100.0
    val actualPrice = amount / (1 - decimalDiscount)
    return actualPrice.toInt().toString()
}