package com.example.uni_lost_and_found_app.features.admin.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uni_lost_and_found_app.features.admin.application.usecase.DeleteUserUseCase
import com.example.uni_lost_and_found_app.features.admin.application.usecase.DemoteUserUseCase
import com.example.uni_lost_and_found_app.features.admin.application.usecase.GetAllUsersUseCase
import com.example.uni_lost_and_found_app.features.admin.application.usecase.PromoteUserUseCase
import com.example.uni_lost_and_found_app.features.admin.domain.model.AdminUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AdminViewModel(
    private val getAllUsersUseCase: GetAllUsersUseCase,
    private val deleteUserUseCase: DeleteUserUseCase,
    private val promoteUserUseCase: PromoteUserUseCase,
    private val demoteUserUseCase: DemoteUserUseCase
) : ViewModel() {

    private val _users = MutableStateFlow<List<AdminUser>>(emptyList())
    val users: StateFlow<List<AdminUser>> = _users

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun loadUsers(token: String) {
        viewModelScope.launch {
            try {
                _users.value = getAllUsersUseCase(token)
                _error.value = null
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun deleteUser(userId: String, token: String) {
        viewModelScope.launch {
            try {
                val success = deleteUserUseCase(userId, token)
                if (success) {
                    loadUsers(token)
                } else {
                    _error.value = "Failed to delete user"
                }
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun promoteUser(userId: String, token: String) {
        viewModelScope.launch {
            try {
                val success = promoteUserUseCase(userId, token)
                if (success) {
                    loadUsers(token)
                } else {
                    _error.value = "Failed to promote user"
                }
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun demoteUser(userId: String, token: String) {
        viewModelScope.launch {
            try {
                val success = demoteUserUseCase(userId, token)
                if (success) {
                    loadUsers(token)
                } else {
                    _error.value = "Failed to demote user"
                }
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }
}
