package com.example.uni_lost_and_found_app.features.chat.domain.model

import java.util.Date

data class Chat(
    val id: String,
    val participants: List<User>,
    val lastMessage: Message?,
    val createdAt: Date,
    val updatedAt: Date
)

data class Message(
    val id: String,
    val senderId: String,
    val content: String,
    val timestamp: Date,
    val isRead: Boolean = false
)

data class User(
    val id: String,
    val fullName: String,
    val email: String,
    val profilePicture: String? = null
) 