package com.example.uni_lost_and_found_app.features.profile.data.repository

import com.example.uni_lost_and_found_app.features.profile.domain.model.UserProfile
import com.example.uni_lost_and_found_app.features.profile.domain.repository.ProfileRepository
import com.example.uni_lost_and_found_app.features.profile.data.api.UserApiService
import com.example.uni_lost_and_found_app.features.profile.data.dto.UserProfileDto
import com.example.uni_lost_and_found_app.features.item.data.model.ItemDto
import com.example.uni_lost_and_found_app.features.item.domain.model.Item
import com.example.uni_lost_and_found_app.features.item.domain.model.ItemStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

fun UserProfileDto.toDomain(): UserProfile = UserProfile(id, name, email, avatarUrl)
fun UserProfile.toDto(): UserProfileDto = UserProfileDto(id, name, email, avatarUrl)

fun ItemDto.toDomain(): Item = Item(
    id = id,
    title = title,
    description = description,
    category = category,
    location = location,
    // You may want to parse date string to Date if needed
    imageUrl = imageUrl,
    status = when (status) {
        "LOST" -> ItemStatus.LOST
        "FOUND" -> ItemStatus.FOUND
        "CLAIMED" -> ItemStatus.CLAIMED
        else -> ItemStatus.LOST
    },
    userId = userId
)

class ProfileRepositoryImpl(private val api: UserApiService) : ProfileRepository {
    override suspend fun getUserProfile(): UserProfile = withContext(Dispatchers.IO) {
        api.getProfile().toDomain()
    }

    suspend fun updateProfile(profile: UserProfile) = withContext(Dispatchers.IO) {
        api.updateProfile(profile.toDto())
    }

    suspend fun getMyItems(): List<Item> = withContext(Dispatchers.IO) {
        api.getMyItems().map { it.toDomain() }
    }

    override suspend fun logOut() {
        // TODO: Implement log out logic (e.g., clear tokens)
    }

    override suspend fun deleteAccount() {
        // TODO: Implement delete account logic
    }
}
