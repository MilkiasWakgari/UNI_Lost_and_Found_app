package com.example.uni_lost_and_found_app.features.admin.data.repository

import com.example.uni_lost_and_found_app.features.admin.data.api.AdminApi
import com.example.uni_lost_and_found_app.features.admin.data.dto.AdminUserDto
import com.example.uni_lost_and_found_app.features.admin.domain.model.AdminUser
import com.example.uni_lost_and_found_app.features.admin.domain.repository.AdminRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AdminRepositoryImpl(private val api: AdminApi) : AdminRepository {
    override suspend fun getAllUsers(token: String): List<AdminUser> = withContext(Dispatchers.IO) {
        try {
            val response = api.getAllUsers("Bearer $token")
            if (response.isSuccessful) {
                response.body()?.map { it.toDomain() } ?: emptyList()
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun deleteUser(userId: String, token: String): Boolean = withContext(Dispatchers.IO) {
        try {
            val response = api.deleteUser(userId, "Bearer $token")
            response.isSuccessful
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun promoteUser(userId: String, token: String): Boolean = withContext(Dispatchers.IO) {
        try {
            val response = api.promoteUser(userId, "Bearer $token")
            response.isSuccessful
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun demoteUser(userId: String, token: String): Boolean = withContext(Dispatchers.IO) {
        try {
            val response = api.demoteUser(userId, "Bearer $token")
            response.isSuccessful
        } catch (e: Exception) {
            false
        }
    }

    private fun AdminUserDto.toDomain(): AdminUser {
        return AdminUser(
            id = id,
            name = name,
            email = email,
            role = role
        )
    }
} 