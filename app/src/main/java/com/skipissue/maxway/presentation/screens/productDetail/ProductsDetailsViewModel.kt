package com.skipissue.maxway.presentation.screens.productDetail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skipissue.maxway.data.constants.State
import com.skipissue.maxway.data.local.DataBaseDataSource
import com.skipissue.maxway.data.local.entity.BasketEntity
import com.skipissue.maxway.domain.GetProductsWithDetailUseCase
import com.skipissue.maxway.domain.entity.responses.ProductsDetailResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsDetailsViewModel @Inject constructor(
    private val getProductWithD: GetProductsWithDetailUseCase,
    private val dataBaseDataSource: DataBaseDataSource,
) : ViewModel() {
    private val _successFlow = MutableSharedFlow<ProductsDetailResponse>()
    val stateSuccess: SharedFlow<ProductsDetailResponse> = _successFlow


    private val _errorFlow = MutableSharedFlow<Int>()
    val errorFlow: SharedFlow<Int> = _errorFlow

    private val _networkFlow = MutableSharedFlow<Unit>()
    val networkFlow: SharedFlow<Unit> = _networkFlow

    val loading = MutableLiveData(false)

    val count = MutableLiveData<Int>(0)


    init {
        // getProductsWithDetails("ed7654f9-a6e5-45a9-b8a0-9f150610cc74")

    }

    // fun getProducts() {
    //     loading.postValue(true)
    //     viewModelScope.launch {
    //         try {
    //             val state = getProducts.invoke()
    //             handleState(state)
    //         }catch (e:Exception){

    //         }finally {
    //             loading.postValue(false)
    //         }
    //     }
    // }

    fun getProductsWithDetails(id: String) {
        viewModelScope.launch {
            try {
                val state = getProductWithD.invoke(id)
                handleState(state)
            } catch (e: Exception) {

            }
        }
    }


    fun saveBasket(
        name: String,
        productId: String,
        description: String,
        cost: String,
        image: String,
        count: Int

    ) {
        viewModelScope.launch {

            if (dataBaseDataSource.getByProductId(productId).isEmpty()) {
                dataBaseDataSource.insert(
                    BasketEntity(
                        name = name,
                        productId = productId,
                        description = description,
                        count = count,
                        image = image,
                        cost = cost
                    )
                )
            } else {
                 var a=dataBaseDataSource.getByProductId(productId).get(0).count!!
                Log.d("ll", "$a ")
                dataBaseDataSource.updateProductPrice(productId,a+count)

            }
        }
    }

    fun getByProductId(productId: String) {

        viewModelScope.launch {
            count.postValue(dataBaseDataSource.getByProductId(productId).size)
            Log.d("oo", "${dataBaseDataSource.getByProductId(productId).size} ")
        }
    }

    private suspend fun handleState(state: State) {
        when (state) {
            is State.Error -> _errorFlow.emit(state.code)
            State.NoNetwork -> _networkFlow.emit(Unit)
            is State.Success<*> -> _successFlow.emit(state.data as ProductsDetailResponse)

        }
    }
}