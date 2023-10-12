package com.ykv17.luxebloom.domain.repository

import com.ykv17.luxebloom.domain.entity.CategoriesEntity
import com.ykv17.luxebloom.domain.entity.ProductEntity
import com.ykv17.luxebloom.domain.entity.ProductsEntity
import com.ykv17.luxebloom.util.Resource

interface ProductRepository {

    suspend fun getAllProducts(): Resource<ProductsEntity>
    suspend fun getProductsByCategory(category: String): Resource<ProductsEntity>

    suspend fun getProductDetails(productId: Int): Resource<ProductEntity>

    suspend fun getAllCategories(): Resource<CategoriesEntity>
}