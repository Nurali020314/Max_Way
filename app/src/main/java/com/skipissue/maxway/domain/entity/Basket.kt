package com.skipissue.maxway.domain.entity

data class Basket(
    val id : Int = 0,
    val name: String?,
    val description: String?,
    val cost: String?,
    val image:String?,
    val count: Int?,
)
