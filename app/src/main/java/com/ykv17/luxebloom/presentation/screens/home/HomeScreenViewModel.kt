package com.ykv17.luxebloom.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ykv17.luxebloom.domain.usecases.GetAllProductCategoriesUseCase
import com.ykv17.luxebloom.domain.usecases.GetAllProductsUseCase
import com.ykv17.luxebloom.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getAllProductCategoriesUseCase: GetAllProductCategoriesUseCase,
    private val getAllProductsUseCase: GetAllProductsUseCase
) : ViewModel() {

    init {
        getAllCategories()
    }

    private fun getAllProducts() {
        viewModelScope.launch {
            getAllProductsUseCase()
        }
    }

    private fun getAllCategories() {
        viewModelScope.launch {
            getAllProductCategoriesUseCase()
                .collectLatest { resp ->
                    when(resp){
                        is Resource.Error -> {

                        }
                        is Resource.Loading -> {

                        }
                        is Resource.Success -> {

                        }
                    }
                }
        }
    }

}