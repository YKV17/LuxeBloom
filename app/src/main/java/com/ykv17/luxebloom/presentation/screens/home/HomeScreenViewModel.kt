package com.ykv17.luxebloom.presentation.screens.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ykv17.luxebloom.domain.usecases.GetAllProductCategoriesUseCase
import com.ykv17.luxebloom.domain.usecases.GetAllProductsUseCase
import com.ykv17.luxebloom.domain.usecases.GetProductsByCategoryUseCase
import com.ykv17.luxebloom.presentation.screens.home.state.CategoryListItem
import com.ykv17.luxebloom.presentation.screens.home.state.CategoryListState
import com.ykv17.luxebloom.presentation.screens.home.state.HomeUiState
import com.ykv17.luxebloom.presentation.screens.home.state.ProductListItem
import com.ykv17.luxebloom.presentation.screens.home.state.ProductListState
import com.ykv17.luxebloom.presentation.toCategories
import com.ykv17.luxebloom.presentation.toProducts
import com.ykv17.luxebloom.util.Resource
import com.ykv17.luxebloom.util.StateUtils.updateState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getAllProductCategoriesUseCase: GetAllProductCategoriesUseCase,
    private val getAllProductsUseCase: GetAllProductsUseCase,
    private val getProductByCategoryUseCase: GetProductsByCategoryUseCase,
) : ViewModel() {

    var categoryListState = mutableStateOf(CategoryListState())
    var productListState = mutableStateOf(ProductListState())


    init {
        getAllCategories()
    }

    fun getAllProducts() {
        viewModelScope.launch {
            getAllProductsUseCase()
                .collectLatest { resp ->
                    when (resp) {
                        is Resource.Loading -> {
                            updateState(productListState) {
                                it.copy(
                                    isLoading = true,
                                    isError = false
                                )
                            }
                        }

                        is Resource.Success -> {
                            updateState(productListState) {
                                it.copy(
                                    isLoading = false,
                                    isError = false,
                                    products = resp.data!!.toProducts()

                                )

                            }
                        }

                        is Resource.Error -> {
                            updateState(productListState) {
                                it.copy(
                                    isLoading = false,
                                    isError = true,
                                    errorMessage = resp.message!!
                                )
                            }
                        }
                    }
                }
        }
    }

    fun getProductByCategory(category: String) {
        viewModelScope.launch {
            getProductByCategoryUseCase(category)
                .collectLatest { resp ->
                    when (resp) {
                        is Resource.Loading -> {
                            updateState(productListState) {
                                it.copy(
                                    isLoading = true,
                                    isError = false
                                )
                            }
                        }

                        is Resource.Success -> {
                            updateState(productListState) {
                                it.copy(
                                    isLoading = false,
                                    isError = false,
                                    products = resp.data!!.toProducts()

                                )

                            }
                        }

                        is Resource.Error -> {
                            updateState(productListState) {
                                it.copy(
                                    isLoading = false,
                                    isError = true,
                                    errorMessage = resp.message!!
                                )
                            }
                        }
                    }
                }
        }
    }

    private fun getAllCategories() {
        viewModelScope.launch {
            getAllProductCategoriesUseCase()
                .collectLatest { resp ->
                    when (resp) {
                        is Resource.Loading -> {
                            updateState(categoryListState) {
                                it.copy(
                                    isLoading = true,
                                    isError = false
                                )
                            }
                        }

                        is Resource.Success -> {
                            updateState(categoryListState) {
                                it.copy(
                                    isLoading = false,
                                    isError = false,
                                    categories = listOf(
                                        CategoryListItem.CategoryItem(
                                            "All",
                                            "",
                                            true
                                        )
                                    ) + resp.data!!.toCategories()
                                )

                            }
                        }

                        is Resource.Error -> {
                            updateState(categoryListState) {
                                it.copy(
                                    isLoading = false,
                                    isError = true,
                                    errorMessage = resp.message!!
                                )
                            }
                        }
                    }
                }
        }
    }
}