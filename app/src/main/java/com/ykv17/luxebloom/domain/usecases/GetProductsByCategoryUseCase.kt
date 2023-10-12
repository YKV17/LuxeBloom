package com.ykv17.luxebloom.domain.usecases

import com.ykv17.luxebloom.domain.entity.ProductsEntity
import com.ykv17.luxebloom.util.Resource
import kotlinx.coroutines.flow.Flow

interface GetProductsByCategoryUseCase {
    suspend operator fun invoke(category: String): Flow<Resource<ProductsEntity>>
}