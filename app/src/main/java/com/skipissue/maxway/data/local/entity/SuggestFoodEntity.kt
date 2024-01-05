package com.skipissue.maxway.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "suggest")
class SuggestFoodEntity (
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val name: String?,
    val description: String?,
    val cost: String?,
    val image:String?

    )


