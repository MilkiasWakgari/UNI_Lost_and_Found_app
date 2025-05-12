package com.example.uni_lost_and_found_app.features.item.data.repository

import android.annotation.SuppressLint
import com.example.uni_lost_and_found_app.features.item.data.api.ItemApiService
import com.example.uni_lost_and_found_app.features.item.data.model.Item as DataItem
import com.example.uni_lost_and_found_app.features.item.data.model.ItemStatus as DataItemStatus
import com.example.uni_lost_and_found_app.features.item.domain.model.Item as DomainItem
import com.example.uni_lost_and_found_app.features.item.domain.model.ItemStatus as DomainItemStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Date
import com.google.gson.Gson
import com.example.uni_lost_and_found_app.features.auth.data.TokenManager

class ItemRepositoryImpl(
    private val apiService: ItemApiService,
    private val tokenManager: TokenManager
) : ItemRepository {

    private fun DataItem.toDomain(): DomainItem {
        return DomainItem(
            id = id,
            title = title,
            description = description,
            category = category,
            location = location,
            date = date,
            imageUrl = imageUrl,
            status = when (status) {
                DataItemStatus.LOST -> DomainItemStatus.LOST
                DataItemStatus.FOUND -> DomainItemStatus.FOUND
                DataItemStatus.CLAIMED -> DomainItemStatus.CLAIMED
            },
            userId = userId
        )
    }

    override suspend fun getAllItems(): Result<List<DomainItem>> = withContext(Dispatchers.IO) {
        try {
            val response = apiService.getAllItems()
            if (response.isSuccessful) {
                Result.success(response.body()?.map { it.toDomain() } ?: emptyList())
            } else {
                Result.failure(Exception("Failed to fetch items"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getRecentItems(days: Int = 7): Result<List<DomainItem>> = withContext(Dispatchers.IO) {
        try {
            val response = apiService.getRecentItems(days)
            if (response.isSuccessful) {
                Result.success(response.body()?.map { it.toDomain() } ?: emptyList())
            } else {
                Result.failure(Exception("Failed to fetch recent items"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getTodayItems(): Result<List<DomainItem>> = withContext(Dispatchers.IO) {
        try {
            val response = apiService.getTodayItems()
            if (response.isSuccessful) {
                Result.success(response.body()?.map { it.toDomain() } ?: emptyList())
            } else {
                Result.failure(Exception("Failed to fetch today's items"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    @SuppressLint("SimpleDateFormat")
    override suspend fun createItem(
        name: String,
        description: String,
        location: String,
        category: String,
        imageUrl: String
    ): Result<DomainItem> {
        return withContext(Dispatchers.IO) {
            try {
                val token = tokenManager.getToken()
                if (token == null) {
                    return@withContext Result.failure(Exception("Not authenticated"))
                }

                val itemData = mapOf(
                    "name" to name,
                    "description" to description,
                    "location" to location,
                    "category" to category,
                    "imageUrl" to imageUrl
                )

                val response = apiService.createItem("Bearer $token", itemData)
                if (response.isSuccessful) {
                    response.body()?.let {
                        Result.success(it.toDomain())
                    } ?: Result.failure(Exception("Empty response body"))
                } else {
                    val errorBody = response.errorBody()?.string()
                    val errorMessage = try {
                        val errorJson = Gson().fromJson(errorBody, Map::class.java)
                        errorJson["message"] as? String ?: "Unknown error"
                    } catch (e: Exception) {
                        errorBody ?: "Unknown error"
                    }
                    
                    val errorCode = response.code()
                    val finalErrorMessage = when (errorCode) {
                        401 -> "Authentication required. Please sign in again."
                        400 -> "Invalid input data: $errorMessage"
                        404 -> "Server endpoint not found. Please check the API URL"
                        500 -> "Server error: $errorMessage"
                        else -> "Failed to create item (HTTP $errorCode): $errorMessage"
                    }
                    Result.failure(Exception(finalErrorMessage))
                }
            } catch (e: Exception) {
                Result.failure(Exception("Network error: ${e.message}"))
            }
        }
    }

    override suspend fun claimItem(id: String): Result<DomainItem> = withContext(Dispatchers.IO) {
        try {
            val response = apiService.claimItem(id)
            if (response.isSuccessful) {
                Result.success(response.body()!!.toDomain())
            } else {
                Result.failure(Exception("Failed to claim item"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun markAsReturned(id: String): Result<DomainItem> = withContext(Dispatchers.IO) {
        try {
            val response = apiService.markAsReturned(id)
            if (response.isSuccessful) {
                Result.success(response.body()!!.toDomain())
            } else {
                Result.failure(Exception("Failed to mark item as returned"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
} 