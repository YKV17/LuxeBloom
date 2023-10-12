package com.ykv17.luxebloom.data.repository

import com.ykv17.luxebloom.data.remote.apis.ProductsApi
import com.ykv17.luxebloom.data.remote.dto.ProductsDto
import com.ykv17.luxebloom.data.remote.mapper.toCategoriesEntity
import com.ykv17.luxebloom.data.remote.mapper.toProductEntity
import com.ykv17.luxebloom.data.remote.mapper.toProductsEntity
import com.ykv17.luxebloom.domain.entity.CategoriesEntity
import com.ykv17.luxebloom.domain.entity.ProductEntity
import com.ykv17.luxebloom.domain.entity.ProductsEntity
import com.ykv17.luxebloom.domain.repository.ProductRepository
import com.ykv17.luxebloom.util.Resource
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productsApi: ProductsApi
) : ProductRepository{

    override suspend fun getAllProducts(): Resource<ProductsEntity> {
        val response = try {
            productsApi.getAllProducts()
        } catch (e: Exception) {
            return Resource.Error(message = e.message ?: "An unknown error occurred.")
        }
        return Resource.Success(response.toProductsEntity())
    }

    override suspend fun getProductsByCategory(category: String): Resource<ProductsEntity> {
        val response = try {
            productsApi.getProductsByCategory(category)
        } catch (e: Exception) {
            return Resource.Error(message = e.message ?: "An unknown error occurred.")
        }
        return Resource.Success(response.toProductsEntity())
    }

    override suspend fun getProductDetails(productId: Int): Resource<ProductEntity> {
        val response = try {
            productsApi.getProductDetails(productId)
        } catch (e: Exception) {
            return Resource.Error(e.message ?: "An unknown error occurred.")
        }
        return Resource.Success(response.toProductEntity())
    }

    override suspend fun getAllCategories(): Resource<CategoriesEntity> {
        val response = try {
            productsApi.getAllCategories()
        } catch (e: Exception) {
            e.printStackTrace()
            return Resource.Error(e.message ?: "An unknown error occurred.")
        }
        return Resource.Success(response.toCategoriesEntity())
    }

}