package com.example.uni_lost_and_found_app.features.profile.data.dto

data class UserProfileDto(
    val id: String,
    val name: String,
    val email: String,
    val avatarUrl: String? = null
) 