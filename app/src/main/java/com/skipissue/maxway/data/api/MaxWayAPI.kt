package com.skipissue.maxway.data.api

import com.skipissue.maxway.domain.entity.SuggestResponse
import com.skipissue.maxway.domain.entity.responses.ProductsDetailResponse
import com.skipissue.maxway.domain.entity.responses.ProductsResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface MaxWayAPI {
    @GET("v1/core/get/category-with-products/")
    suspend fun getProducts(): Response<ProductsResponse>

    @GET("v1/core/get/product-detail/ed7654f9-a6e5-45a9-b8a0-9f150610cc74/?format=json")
    suspend fun getProductsWithDetail(): Response<ProductsDetailResponse>

    @GET("v1/core/get/product-detail/{id}/?format=json")
    suspend fun getDetails(@Path("id") id: String): Response<ProductsDetailResponse>

}