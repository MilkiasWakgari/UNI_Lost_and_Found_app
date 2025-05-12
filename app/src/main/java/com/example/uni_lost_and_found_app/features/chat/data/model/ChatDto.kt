package com.example.uni_lost_and_found_app.features.chat.data.model

import com.example.uni_lost_and_found_app.features.chat.domain.model.Chat
import com.example.uni_lost_and_found_app.features.chat.domain.model.Message
import com.example.uni_lost_and_found_app.features.chat.domain.model.User
import java.util.Date

data class ChatDto(
    val id: String,
    val participants: List<UserDto>,
    val lastMessage: MessageDto?,
    val createdAt: Date,
    val updatedAt: Date
) {
    fun toDomain(): Chat {
        return Chat(
            id = id,
            participants = participants.map { it.toDomain() },
            lastMessage = lastMessage?.toDomain(),
            createdAt = createdAt,
            updatedAt = updatedAt
        )
    }
}

data class MessageDto(
    val id: String,
    val senderId: String,
    val content: String,
    val timestamp: Date,
    val isRead: Boolean = false
) {
    fun toDomain(): Message {
        return Message(
            id = id,
            senderId = senderId,
            content = content,
            timestamp = timestamp,
            isRead = isRead
        )
    }
}

data class UserDto(
    val id: String,
    val fullName: String,
    val email: String,
    val profilePicture: String? = null
) {
    fun toDomain(): User {
        return User(
            id = id,
            fullName = fullName,
            email = email,
            profilePicture = profilePicture
        )
    }
} 