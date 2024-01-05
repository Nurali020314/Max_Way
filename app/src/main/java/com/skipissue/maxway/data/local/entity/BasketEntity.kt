package com.skipissue.maxway.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "basket")
class BasketEntity (
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val productId:String?,
    val name: String?,
    val description: String?,
    val cost: String?,
    val image:String?,
    val count: Int?,


    )