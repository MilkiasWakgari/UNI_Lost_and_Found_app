package com.example.uni_lost_and_found_app.features.chat.data.repository

import com.example.uni_lost_and_found_app.features.chat.data.api.ChatApiService
import com.example.uni_lost_and_found_app.features.chat.domain.model.Chat
import com.example.uni_lost_and_found_app.features.chat.domain.model.Message
import com.example.uni_lost_and_found_app.features.chat.domain.repository.ChatRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ChatRepositoryImpl(private val apiService: ChatApiService) : ChatRepository {
    override suspend fun getChats(): Result<List<Chat>> = withContext(Dispatchers.IO) {
        try {
            val chatDtos = apiService.getChats()
            Result.success(chatDtos.map { it.toDomain() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getChatById(chatId: String): Result<Chat> = withContext(Dispatchers.IO) {
        try {
            val chatDto = apiService.getChatById(chatId)
            Result.success(chatDto.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun createChat(otherUserId: String): Result<Chat> = withContext(Dispatchers.IO) {
        try {
            val chatDto = apiService.createChat(mapOf("otherUserId" to otherUserId))
            Result.success(chatDto.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getChatMessages(chatId: String): Result<List<Message>> = withContext(Dispatchers.IO) {
        try {
            val messageDtos = apiService.getMessages(chatId)
            Result.success(messageDtos.map { it.toDomain() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun sendMessage(chatId: String, content: String): Result<Message> = withContext(Dispatchers.IO) {
        try {
            val messageDto = apiService.sendMessage(chatId, mapOf("content" to content))
            Result.success(messageDto.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun markMessageAsRead(messageId: String): Result<Message> = withContext(Dispatchers.IO) {
        try {
            val messageDto = apiService.markMessageAsRead(messageId)
            Result.success(messageDto.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun markAllMessagesAsRead(chatId: String): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            apiService.markAllMessagesAsRead(chatId)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
} 