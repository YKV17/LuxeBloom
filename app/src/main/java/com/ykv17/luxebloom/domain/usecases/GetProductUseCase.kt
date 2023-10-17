package com.ykv17.luxebloom.domain.usecases

import com.ykv17.luxebloom.domain.entity.ProductEntity
import com.ykv17.luxebloom.domain.entity.ProductsEntity
import com.ykv17.luxebloom.util.Resource
import kotlinx.coroutines.flow.Flow

interface GetProductUseCase {
    suspend operator fun invoke(productId: String): Flow<Resource<ProductEntity>>
}