package com.ykv17.luxebloom.data.repository

import com.ykv17.luxebloom.data.remote.apis.ProductsApi
import com.ykv17.luxebloom.domain.repository.ProductRepository
import com.ykv17.luxebloom.util.Resource
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productsApi: ProductsApi
) : ProductRepository{

    override suspend fun getAllProducts(): Resource<Any> {
        val response = try {
            productsApi.getAllProducts()
        } catch (e: Exception) {
            return Resource.Error(e.message ?: "An unknown error occurred.")
        }
        return Resource.Success(response)
    }

    override suspend fun getProductDetails(productId: Int): Resource<Any> {
        val response = try {
            productsApi.getProductDetails(productId)
        } catch (e: Exception) {
            return Resource.Error(e.message ?: "An unknown error occurred.")
        }
        return Resource.Success(response)
    }

    override suspend fun getAllCategories(): Resource<Any> {
        val response = try {
            productsApi.getAllCategories()
        } catch (e: Exception) {
            return Resource.Error(e.message ?: "An unknown error occurred.")
        }
        return Resource.Success(response)
    }

}