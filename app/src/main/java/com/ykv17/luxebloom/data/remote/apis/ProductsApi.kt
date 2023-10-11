package com.ykv17.luxebloom.data.remote.apis

import com.ykv17.luxebloom.util.Resource
import retrofit2.http.GET

interface ProductsApi {

    @GET("products")
    suspend fun getAllProducts(): Resource<Any>

    @GET("products/{id}")
    suspend fun getProductDetails(productId: Int): Resource<Any>

    @GET("/products/products/categories")
    suspend fun getAllCategories(): Resource<Any>

}