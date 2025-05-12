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

class ItemRepository(private val apiService: ItemApiService) {

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
                DataItemStatus.lost -> DomainItemStatus.LOST
                DataItemStatus.found -> DomainItemStatus.FOUND
                DataItemStatus.claimed -> DomainItemStatus.CLAIMED
            },
            userId = userId
        )
    }

    suspend fun getAllItems(): Result<List<DomainItem>> = withContext(Dispatchers.IO) {
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

    suspend fun getRecentItems(days: Int = 7): Result<List<DomainItem>> = withContext(Dispatchers.IO) {
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

    suspend fun getTodayItems(): Result<List<DomainItem>> = withContext(Dispatchers.IO) {
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
    suspend fun createItem(
        title: String,
        description: String,
        location: String,
        category: String,
        imageUrl: String? = null
    ): Result<DomainItem> = withContext(Dispatchers.IO) {
        try {
            val itemData = mapOf(
                "title" to title,
                "description" to description,
                "location" to location,
                "category" to category,
                "timeFound" to java.text.SimpleDateFormat("HH:mm").format(Date()),
                "imageUrl" to (imageUrl ?: ""),
                "status" to "found"

            )
            val response = apiService.createItem(itemData)
            if (response.isSuccessful) {
                Result.success(response.body()!!.toDomain())
            } else {

                Result.failure(Exception("Failed to create item"))
            }
        } catch (e: Exception) {

            Result.failure(e)
        }
    }

    suspend fun claimItem(id: String): Result<DomainItem> = withContext(Dispatchers.IO) {
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

    suspend fun markAsReturned(id: String): Result<DomainItem> = withContext(Dispatchers.IO) {
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