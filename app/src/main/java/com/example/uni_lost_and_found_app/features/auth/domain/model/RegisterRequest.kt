package com.example.uni_lost_and_found_app.features.auth.domain.model

data class RegisterRequest(val name: String, val email: String, val password: String)
data class LoginRequest(val email: String, val password: String)
data class ForgotPasswordRequest(val email: String)

data class LoginResponse(val token: String, val user: UserDto)
data class ResetCodeResponse(val code: String) // simulate email
data class UserDto(val id: String, val name: String, val email: String)
