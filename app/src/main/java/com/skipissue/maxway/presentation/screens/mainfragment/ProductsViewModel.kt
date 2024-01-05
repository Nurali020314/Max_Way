package com.skipissue.maxway.presentation.screens.mainfragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skipissue.maxway.data.constants.State
import com.skipissue.maxway.domain.GetProductsUseCase
import com.skipissue.maxway.domain.GetProductsWithDetailUseCase
import com.skipissue.maxway.domain.entity.responses.ProductsResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val getProducts: GetProductsUseCase,
    private val getProductWithD: GetProductsWithDetailUseCase,

) : ViewModel() {
    private val _successFlow = MutableSharedFlow<ProductsResponse>()
    val stateSuccess: SharedFlow<ProductsResponse> = _successFlow

    private val _errorFlow = MutableSharedFlow<Int>()
    val errorFlow: SharedFlow<Int> = _errorFlow

    private val _networkFlow = MutableSharedFlow<Unit>()
    val networkFlow: SharedFlow<Unit> = _networkFlow

    val loading = MutableLiveData(false)


    init {
        getProducts()
    }

    fun getProducts() {
        loading.postValue(true)
        viewModelScope.launch {
            try {
                val state = getProducts.invoke()

                handleState(state)

            }catch (e:Exception){

            }finally {
                loading.postValue(false)
            }
        }
    }

   // fun getProductsWithDetails() {
   //     viewModelScope.launch {
   //         val state = getProductWithD.invoke()
   //         handleState(state)
   //     }
   // }

    private suspend fun handleState(state: State) {
        when (state) {
            is State.Error -> _errorFlow.emit(state.code)
            State.NoNetwork -> _networkFlow.emit(Unit)
            is State.Success<*> -> _successFlow.emit(state.data as ProductsResponse)
        }
    }
}