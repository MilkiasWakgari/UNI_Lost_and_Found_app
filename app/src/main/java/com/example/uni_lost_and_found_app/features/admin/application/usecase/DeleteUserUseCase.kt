package com.example.uni_lost_and_found_app.features.admin.application.usecase

import com.example.uni_lost_and_found_app.features.admin.domain.repository.AdminRepository

class DeleteUserUseCase(private val repository: AdminRepository) {
    suspend operator fun invoke(userId: String, token: String): Boolean {
        return repository.deleteUser(userId, token)
    }
} 