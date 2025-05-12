package com.example.uni_lost_and_found_app.features.admin.application.usecase

import com.example.uni_lost_and_found_app.features.admin.domain.model.AdminUser
import com.example.uni_lost_and_found_app.features.admin.domain.repository.AdminRepository

class GetAllUsersUseCase(private val repository: AdminRepository) {
    suspend operator fun invoke(token: String): List<AdminUser> {
        return repository.getAllUsers(token)
    }
} 