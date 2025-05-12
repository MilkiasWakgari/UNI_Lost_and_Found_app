package com.example.uni_lost_and_found_app.features.profile.domain.repository

import com.example.uni_lost_and_found_app.features.profile.domain.model.UserProfile

interface ProfileRepository {
    suspend fun getUserProfile(): UserProfile
    suspend fun logOut()
    suspend fun deleteAccount()
}
