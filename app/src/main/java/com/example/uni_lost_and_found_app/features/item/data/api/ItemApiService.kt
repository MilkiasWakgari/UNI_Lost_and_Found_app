package com.example.uni_lost_and_found_app.features.item.data.api

import com.example.uni_lost_and_found_app.features.item.data.model.Item
import retrofit2.Response
import retrofit2.http.*

interface ItemApiService {
    @GET("items")
    suspend fun getAllItems(): Response<List<Item>>

    @GET("items/recent")
    suspend fun getRecentItems(@Query("days") days: Int = 7): Response<List<Item>>

    @GET("items/today")
    suspend fun getTodayItems(): Response<List<Item>>

    @GET("items/{id}")
    suspend fun getItemById(@Path("id") id: String): Response<Item>

    @POST("items")
    suspend fun createItem(@Body item: Map<String, String>): Response<Item>

    @GET("items/found")
    suspend fun getItemsByFoundBy(): Response<List<Item>>

    @GET("items/claimed")
    suspend fun getItemsByClaimedBy(): Response<List<Item>>

    @PUT("items/{id}")
    suspend fun updateItem(@Path("id") id: String, @Body item: Map<String, Any>): Response<Item>

    @DELETE("items/{id}")
    suspend fun deleteItem(@Path("id") id: String): Response<Unit>

    @POST("items/{id}/claim")
    suspend fun claimItem(@Path("id") id: String): Response<Item>

    @POST("items/{id}/return")
    suspend fun markAsReturned(@Path("id") id: String): Response<Item>
} 