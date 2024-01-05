package com.skipissue.maxway.presentation.adapter

data class SuggestBasket(
    val id : Int = 0,
    val nameUz: String?,
    val nameRu: String?,
    val nameEng: String?,
    val descriptionUZ: String?,
    val descriptionRu: String?,
    val descriptionEng: String?,
    val cost: String?,
    val image:String?,
    val count: Int?,
)
