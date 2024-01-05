package com.skipissue.maxway.data.datasource

import com.skipissue.maxway.domain.entity.SuggestResponse
import com.skipissue.maxway.domain.entity.responses.ProductsDetailResponse
import com.skipissue.maxway.domain.entity.responses.ProductsResponse
import retrofit2.Response


interface ProductsDataSource{
    suspend fun getProducts(): Response<ProductsResponse>
    suspend fun getDetails(id:String): Response<ProductsDetailResponse>

}