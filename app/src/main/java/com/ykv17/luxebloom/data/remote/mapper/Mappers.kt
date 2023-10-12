package com.ykv17.luxebloom.data.remote.mapper

import com.ykv17.luxebloom.data.remote.dto.CategoriesDto
import com.ykv17.luxebloom.data.remote.dto.ProductDto
import com.ykv17.luxebloom.data.remote.dto.ProductsDto
import com.ykv17.luxebloom.domain.entity.CategoriesEntity
import com.ykv17.luxebloom.domain.entity.ProductEntity
import com.ykv17.luxebloom.domain.entity.ProductsEntity

fun ProductsDto?.toProductsEntity(): ProductsEntity {
    return ProductsEntity(
        limit = this?.limit ?: 0,
        products = this?.products?.map { product -> product.toProductEntity() } ?: emptyList(),
        total = this?.total ?: 0,
        skip = this?.skip ?: 0
    )
}

fun ProductDto?.toProductEntity(): ProductEntity {
    return ProductEntity(
        brand = this?.brand ?: "",
        category = this?.category ?: "",
        description = this?.description ?: "",
        discountPercentage = this?.discountPercentage?.toString() ?: "",
        id = this?.id?.toString() ?: "",
        images = this?.images ?: listOf(),
        price = this?.price?.toString() ?: "",
        rating = this?.rating?.toString() ?: "",
        stock = this?.stock?.toString() ?: "",
        thumbnail = this?.thumbnail ?: "",
        title = this?.title ?: ""
    )
}

fun CategoriesDto?.toCategoriesEntity(): CategoriesEntity {
    return CategoriesEntity().also { categoryEntity ->
        this?.let {
            categoryEntity.addAll(it)
        }
    }
}