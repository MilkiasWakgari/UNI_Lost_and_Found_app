package com.example.uni_lost_and_found_app.features.chat.domain.repository

import com.example.uni_lost_and_found_app.features.chat.domain.model.Chat
import com.example.uni_lost_and_found_app.features.chat.domain.model.Message

interface ChatRepository {
    suspend fun getChats(): Result<List<Chat>>
    suspend fun getChatById(chatId: String): Result<Chat>
    suspend fun createChat(otherUserId: String): Result<Chat>
    suspend fun getChatMessages(chatId: String): Result<List<Message>>
    suspend fun sendMessage(chatId: String, content: String): Result<Message>
    suspend fun markMessageAsRead(messageId: String): Result<Message>
    suspend fun markAllMessagesAsRead(chatId: String): Result<Unit>
}
