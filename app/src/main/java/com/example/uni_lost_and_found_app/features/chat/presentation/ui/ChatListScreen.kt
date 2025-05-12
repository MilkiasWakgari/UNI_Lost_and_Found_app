package com.example.uni_lost_and_found_app.features.chat.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.uni_lost_and_found_app.features.chat.domain.model.Chat
import com.example.uni_lost_and_found_app.features.chat.domain.repository.ChatRepository
import kotlinx.coroutines.launch

@Composable
fun ChatListScreen(
    chatRepository: ChatRepository,
    onChatClick: (String) -> Unit
) {
    var chats by remember { mutableStateOf<List<Chat>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf<String?>(null) }
    val scope = rememberCoroutineScope()
    val currentUserId = "current_user_id" // TODO: Replace with actual user ID logic

    LaunchedEffect(Unit) {
        scope.launch {
            chatRepository.getChats().onSuccess { chatList ->
                chats = chatList
                isLoading = false
            }.onFailure { e ->
                error = e.message
                isLoading = false
            }
        }
    }

    Scaffold(
        topBar = {
            Text(
                text = "Messages",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(16.dp)
            )
        }
    ) { padding ->
        if (isLoading) {
            Text("Loading...", modifier = Modifier.padding(16.dp))
        } else if (error != null) {
            Text(error ?: "Unknown error", color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(16.dp))
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
            ) {
                items(chats) { chat ->
                    // For 1:1 chat, show the other participant's name
                    val otherParticipant = chat.participants.firstOrNull { it.id != currentUserId }
                    val displayName = otherParticipant?.fullName ?: "Group Chat"
                    val lastMessage = chat.lastMessage?.content ?: "No messages yet"
                    val lastMessageTime = chat.lastMessage?.timestamp?.toString() ?: ""
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onChatClick(chat.id) }
                            .padding(16.dp)
                    ) {
                        Text(
                            text = displayName,
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = lastMessage,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        if (lastMessageTime.isNotBlank()) {
                            Text(
                                text = lastMessageTime,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        }
    }
}

// Placeholder Chat model for demonstration
// Remove if you already have this in your domain layer
// data class Chat(val id: String, val name: String)