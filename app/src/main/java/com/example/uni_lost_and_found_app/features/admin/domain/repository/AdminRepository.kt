package com.example.uni_lost_and_found_app.features.admin.domain.repository

import com.example.uni_lost_and_found_app.features.admin.domain.model.AdminUser

interface AdminRepository {
    suspend fun getAllUsers(token: String): List<AdminUser>
    suspend fun deleteUser(userId: String, token: String): Boolean
    suspend fun promoteUser(userId: String, token: String): Boolean
    suspend fun demoteUser(userId: String, token: String): Boolean
} 