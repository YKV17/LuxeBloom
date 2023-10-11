package com.ykv17.luxebloom.domain.usecases

import com.ykv17.luxebloom.util.Resource
import kotlinx.coroutines.flow.Flow

interface GetAllProductCategoriesUseCase {
    suspend operator fun invoke(): Flow<Resource<Any>>
}