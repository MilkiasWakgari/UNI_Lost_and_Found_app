package com.example.uni_lost_and_found_app.features.chat.data.api

import com.example.uni_lost_and_found_app.features.chat.data.model.ChatDto
import com.example.uni_lost_and_found_app.features.chat.data.model.MessageDto
import retrofit2.http.*

interface ChatApiService {
    @GET("chats")
    suspend fun getChats(): List<ChatDto>

    @GET("chats/{id}")
    suspend fun getChatById(@Path("id") chatId: String): ChatDto

    @POST("chats/create")
    suspend fun createChat(@Body otherUserId: Map<String, String>): ChatDto

    @GET("chats/{id}/messages")
    suspend fun getMessages(@Path("id") chatId: String): List<MessageDto>

    @POST("chats/{id}/messages")
    suspend fun sendMessage(@Path("id") chatId: String, @Body content: Map<String, String>): MessageDto

    @PUT("chats/messages/{messageId}/read")
    suspend fun markMessageAsRead(@Path("messageId") messageId: String): MessageDto

    @PUT("chats/{id}/messages/read")
    suspend fun markAllMessagesAsRead(@Path("id") chatId: String)
} 