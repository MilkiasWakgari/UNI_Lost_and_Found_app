package com.example.uni_lost_and_found_app.features.profile.domain.model

// Represents a user's profile information
data class UserProfile(
    val id: String,
    val name: String,
    val email: String,
    val avatarUrl: String? = null
)
