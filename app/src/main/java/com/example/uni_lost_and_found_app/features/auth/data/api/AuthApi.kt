package com.example.uni_lost_and_found_app.features.auth.data.api

import com.example.uni_lost_and_found_app.features.auth.domain.model.ForgotPasswordRequest
import com.example.uni_lost_and_found_app.features.auth.domain.model.LoginRequest
import com.example.uni_lost_and_found_app.features.auth.domain.model.LoginResponse
import com.example.uni_lost_and_found_app.features.auth.domain.model.RegisterRequest
import com.example.uni_lost_and_found_app.features.auth.domain.model.ResetCodeResponse
import com.example.uni_lost_and_found_app.features.item.data.model.Item
import retrofit2.Response
import retrofit2.http.*

interface AuthApi {
    @POST("signup")
    suspend fun register(@Body request: RegisterRequest): Response<Unit>

    @POST("signin")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @POST("forgot-password")
    suspend fun forgotPassword(@Body request: ForgotPasswordRequest): Response<ResetCodeResponse>
}
