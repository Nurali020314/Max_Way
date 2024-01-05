package com.skipissue.maxway.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.skipissue.maxway.data.local.entity.BasketEntity

@Dao
interface SuggestDao {
    @Insert
    suspend fun insert(basket: BasketEntity)

    @Query("SELECT * FROM `suggest`")
    suspend fun getAllSuggest(): BasketEntity

    @Query("DELETE FROM `suggest`")
    suspend fun delete()

}