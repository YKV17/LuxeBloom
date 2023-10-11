package com.ykv17.luxebloom.domain.usecases

import com.ykv17.luxebloom.domain.repository.ProductRepository
import com.ykv17.luxebloom.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllProductCategoriesUseCaseImpl @Inject constructor(
    private val productRepository: ProductRepository
) : GetAllProductCategoriesUseCase {
    override suspend fun invoke(): Flow<Resource<Any>> = flow {
        emit(productRepository.getAllProducts())
    }
}