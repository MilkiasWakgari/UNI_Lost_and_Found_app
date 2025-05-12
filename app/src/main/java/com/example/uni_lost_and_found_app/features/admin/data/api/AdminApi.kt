package com.example.uni_lost_and_found_app.features.admin.data.api

import com.example.uni_lost_and_found_app.features.admin.data.dto.AdminUserDto
import retrofit2.Response
import retrofit2.http.*

interface AdminApi {
    @GET("admin/users")
    suspend fun getAllUsers(@Header("Authorization") token: String): Response<List<AdminUserDto>>

    @DELETE("admin/users/{userId}")
    suspend fun deleteUser(@Path("userId") userId: String, @Header("Authorization") token: String): Response<Unit>

    @PUT("admin/users/{userId}/promote")
    suspend fun promoteUser(@Path("userId") userId: String, @Header("Authorization") token: String): Response<Unit>

    @PUT("admin/users/{userId}/demote")
    suspend fun demoteUser(@Path("userId") userId: String, @Header("Authorization") token: String): Response<Unit>
} 