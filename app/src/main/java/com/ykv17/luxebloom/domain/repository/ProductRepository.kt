package com.ykv17.luxebloom.domain.repository

import com.ykv17.luxebloom.util.Resource

interface ProductRepository {

    suspend fun getAllProducts(): Resource<Any>

    suspend fun getProductDetails(productId: Int): Resource<Any>

    suspend fun getAllCategories(): Resource<Any>
}