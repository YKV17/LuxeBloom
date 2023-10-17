package com.ykv17.luxebloom.presentation.screens.product

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ykv17.luxebloom.domain.usecases.GetProductUseCase
import com.ykv17.luxebloom.presentation.screens.product.state.ProductDetailUiState
import com.ykv17.luxebloom.presentation.toProductDetails
import com.ykv17.luxebloom.util.Resource
import com.ykv17.luxebloom.util.StateUtils.updateState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailScreenViewModel @Inject constructor(
    private val getProductUseCase: GetProductUseCase
) : ViewModel() {

    var productDetailState = mutableStateOf(ProductDetailUiState())

    fun getProduct(productId: String) {
        viewModelScope.launch {
            getProductUseCase(productId)
                .collectLatest { resp ->
                    when (resp) {
                        is Resource.Loading -> {
                            updateState(productDetailState) {
                                it.copy(
                                    isLoading = true,
                                    isError = false
                                )
                            }
                        }

                        is Resource.Success -> {
                            updateState(productDetailState) {
                                it.copy(
                                    isLoading = false,
                                    isError = false,
                                    productDetail = resp.data!!.toProductDetails()

                                )

                            }
                        }

                        is Resource.Error -> {
                            updateState(productDetailState) {
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