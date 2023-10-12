package com.ykv17.luxebloom.di.module

import com.ykv17.luxebloom.data.remote.apis.ProductsApi
import com.ykv17.luxebloom.data.repository.ProductRepositoryImpl
import com.ykv17.luxebloom.domain.repository.ProductRepository
import com.ykv17.luxebloom.domain.usecases.GetAllProductCategoriesUseCase
import com.ykv17.luxebloom.domain.usecases.GetAllProductCategoriesUseCaseImpl
import com.ykv17.luxebloom.domain.usecases.GetAllProductsUseCase
import com.ykv17.luxebloom.domain.usecases.GetAllProductsUseCaseImpl
import com.ykv17.luxebloom.domain.usecases.GetProductsByCategoriesUseCaseImpl
import com.ykv17.luxebloom.domain.usecases.GetProductsByCategoryUseCase
import com.ykv17.luxebloom.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideProductsApi(retrofit: Retrofit): ProductsApi {
        return retrofit.create(ProductsApi::class.java)
    }

    @Singleton
    @Provides
    fun provideProductsRepository(
        productsApi: ProductsApi
    ): ProductRepository = ProductRepositoryImpl(productsApi)

    @Singleton
    @Provides
    fun provideGetAllProductsUseCase(
        productRepository: ProductRepository
    ): GetAllProductsUseCase = GetAllProductsUseCaseImpl(productRepository)

    @Singleton
    @Provides
    fun provideGetAllProductCategoriesUseCase(
        productRepository: ProductRepository
    ): GetAllProductCategoriesUseCase = GetAllProductCategoriesUseCaseImpl(productRepository)

    @Singleton
    @Provides
    fun provideGetProductsByCategoriesUseCase(
        productRepository: ProductRepository
    ): GetProductsByCategoryUseCase = GetProductsByCategoriesUseCaseImpl(productRepository)

}