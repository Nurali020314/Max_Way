package com.skipissue.maxway.data.repos

import com.skipissue.maxway.domain.entity.SuggestResponse
import com.skipissue.maxway.domain.entity.responses.ProductsDetailResponse
import com.skipissue.maxway.domain.entity.responses.ProductsResponse
import retrofit2.Response

interface ProductsRepository{
    suspend fun getProducts(): Response<ProductsResponse>
    suspend fun getDetails(id:String): Response<ProductsDetailResponse>
}