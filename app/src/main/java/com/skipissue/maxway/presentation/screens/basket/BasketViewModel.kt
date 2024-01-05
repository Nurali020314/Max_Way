package com.skipissue.maxway.presentation.screens.basket

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skipissue.maxway.data.constants.State
import com.skipissue.maxway.data.local.DataBaseDataSource
import com.skipissue.maxway.data.local.entity.BasketEntity
import com.skipissue.maxway.domain.entity.responses.ProductsResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(private val dataSource: DataBaseDataSource):ViewModel() {
    private val _successFlow = MutableSharedFlow<List<BasketEntity>>()
    val stateSuccess: SharedFlow<List<BasketEntity>> = _successFlow


    init {
        getProducts()
    }

     fun getProducts() {
            viewModelScope.launch {
                try {
                    val state=dataSource.getAllBasket()
                   _successFlow.emit(state)
                }catch (e:Exception){


                }
            }
    }
    fun deleteAll(){
        viewModelScope.launch {
            try {
                dataSource.delete()
            }catch (e:Exception){


            }
        }
    }
    fun  deleteById(id:Int){
        viewModelScope.launch {
            dataSource.deleteById(id)
        }
    }

    fun getByProductId(productId:String):Int{
        var a=0;
        viewModelScope.launch {
          a=dataSource.getByProductId(productId).size

        }
        return a
    }


}