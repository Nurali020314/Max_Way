package com.skipissue.maxway.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.skipissue.maxway.data.local.entity.BasketEntity

@Dao
interface BasketDao {
    @Insert
    suspend fun insert(basket:BasketEntity)

    @Query("SELECT * FROM `basket`")
    suspend fun getAllFromBasket():List<BasketEntity>

    @Query("DELETE FROM `basket`")
    suspend fun delete()

    @Query("DELETE FROM `basket` WHERE id = :id")
    suspend fun deleteById(id:Int)

    @Query("SELECT * FROM `basket` WHERE productId=:productId")
    suspend fun getByProductId(productId:String):List<BasketEntity>

    @Query("UPDATE `basket` SET count=:newCount WHERE productId=:productId")
    fun updateProductPrice(productId: String, newCount: Int)







}