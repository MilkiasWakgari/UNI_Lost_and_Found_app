package com.example.uni_lost_and_found_app.features.profile.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uni_lost_and_found_app.features.profile.domain.model.UserProfile
import com.example.uni_lost_and_found_app.features.profile.domain.repository.ProfileRepository
import com.example.uni_lost_and_found_app.features.item.domain.model.Item
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val repository: ProfileRepository
) : ViewModel() {
    private val _userProfile = MutableStateFlow<UserProfile?>(null)
    val userProfile: StateFlow<UserProfile?> = _userProfile

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val _myItems = MutableStateFlow<List<Item>>(emptyList())
    val myItems: StateFlow<List<Item>> = _myItems

    fun loadUserProfile() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _userProfile.value = repository.getUserProfile()
                _error.value = null
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun logOut() {
        viewModelScope.launch {
            try {
                repository.logOut()
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun deleteAccount() {
        viewModelScope.launch {
            try {
                repository.deleteAccount()
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun updateProfile(profile: UserProfile) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                if (repository is com.example.uni_lost_and_found_app.features.profile.data.repository.ProfileRepositoryImpl) {
                    repository.updateProfile(profile)
                }
                loadUserProfile()
                _error.value = null
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun loadMyItems() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                if (repository is com.example.uni_lost_and_found_app.features.profile.data.repository.ProfileRepositoryImpl) {
                    _myItems.value = repository.getMyItems()
                }
                _error.value = null
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }
}
