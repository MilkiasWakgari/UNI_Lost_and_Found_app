package com.example.uni_lost_and_found_app.features.profile.data.api

import com.example.uni_lost_and_found_app.features.profile.data.dto.UserProfileDto
import com.example.uni_lost_and_found_app.features.item.data.model.ItemDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT

interface UserApiService {
    @GET("user/profile")
    suspend fun getProfile(): UserProfileDto

    @PUT("user/profile")
    suspend fun updateProfile(@Body profile: UserProfileDto): Response<Unit>

    @GET("user/items")
    suspend fun getMyItems(): List<ItemDto>
} 