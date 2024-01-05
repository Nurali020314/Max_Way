package com.skipissue.maxway.data.local

import com.skipissue.maxway.data.local.entity.BasketEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import uz.gita.lesson40.database.AppDataBase
import javax.inject.Inject

class DataBaseDataSourceImpl @Inject constructor(val database: AppDataBase) : DataBaseDataSource {

    override suspend fun insert(basket: BasketEntity) {
        withContext(Dispatchers.IO) {
            database.basketDao().insert(basket)
        }
    }

    override suspend fun getAllBasket(): List<BasketEntity> {
        return withContext(Dispatchers.IO) {
            database.basketDao().getAllFromBasket() }
    }


    override suspend fun delete() {
        withContext(Dispatchers.IO) {
            database.basketDao().delete()
        }
    }

    override suspend fun deleteById(id:Int) {
        withContext(Dispatchers.IO) {
            database.basketDao().deleteById(id)
        }
    }

    override suspend fun getByProductId(productId: String): List<BasketEntity> {
        return withContext(Dispatchers.IO) {
            database.basketDao().getByProductId(productId) }
    }

    override suspend fun updateProductPrice(productId: String, newCount: Int) {
        withContext(Dispatchers.IO) {
           database.basketDao().updateProductPrice(productId,newCount)
        }
    }
}