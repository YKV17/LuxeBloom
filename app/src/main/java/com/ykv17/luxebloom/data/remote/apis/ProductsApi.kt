package com.ykv17.luxebloom.data.remote.apis

import com.ykv17.luxebloom.data.remote.dto.CategoriesDto
import com.ykv17.luxebloom.data.remote.dto.ProductDto
import com.ykv17.luxebloom.data.remote.dto.ProductsDto
import com.ykv17.luxebloom.util.Resource
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductsApi {

    @GET("products")
    suspend fun getAllProducts(): ProductsDto

    @GET("/products/category/{category}")
    suspend fun getProductsByCategory(
        @Path("category") category: String
    ): ProductsDto

    @GET("products/{productId}")
    suspend fun getProductDetails(@Path("productId") productId: String): ProductDto

    @GET("/products/categories")
    suspend fun getAllCategories(): CategoriesDto

}