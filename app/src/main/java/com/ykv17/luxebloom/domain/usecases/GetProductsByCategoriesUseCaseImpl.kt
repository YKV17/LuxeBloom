package com.ykv17.luxebloom.domain.usecases

import com.ykv17.luxebloom.domain.entity.CategoriesEntity
import com.ykv17.luxebloom.domain.entity.ProductsEntity
import com.ykv17.luxebloom.domain.repository.ProductRepository
import com.ykv17.luxebloom.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetProductsByCategoriesUseCaseImpl @Inject constructor(
    private val productRepository: ProductRepository
) : GetProductsByCategoryUseCase {
    override suspend fun invoke(category: String): Flow<Resource<ProductsEntity>> = flow {
        emit(productRepository.getProductsByCategory(category))
    }
}