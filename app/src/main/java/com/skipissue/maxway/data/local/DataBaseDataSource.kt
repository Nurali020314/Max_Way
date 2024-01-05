package com.skipissue.maxway.data.local

import com.skipissue.maxway.data.local.entity.BasketEntity
import com.skipissue.maxway.data.local.entity.SuggestFoodEntity

interface DataBaseDataSource {
    suspend fun insert(basket:BasketEntity)
    suspend fun getAllBasket(): List<BasketEntity>
    suspend fun delete()
    suspend fun deleteById(id:Int)
    suspend fun getByProductId(productId:String): List<BasketEntity>
    suspend fun updateProductPrice(productId: String, newCount: Int)

}