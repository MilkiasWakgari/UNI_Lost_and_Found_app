package com.example.uni_lost_and_found_app.core.presentation.components

import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BottomNavigationBar(
    currentRoute: String,
    onNavigate: (String) -> Unit
) {
    NavigationBar(containerColor = Color(0xFF4A90E2),
            modifier = Modifier.height(60.dp)) {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Search, contentDescription = "Items Found") },
            label = { Text("Found") },
            selected = currentRoute == "items_found",
            onClick = { onNavigate("items_found") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Info, contentDescription = "Items Lost") },
            label = { Text("Lost") },
            selected = currentRoute == "items_lost",
            onClick = { onNavigate("items_lost") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Add, contentDescription = "Report Item") },
            label = { Text("Report") },
            selected = currentRoute == "report_item_found",
            onClick = { onNavigate("report_item_found") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Chat, contentDescription = "Chats") },
            label = { Text("Chats") },
            selected = currentRoute == "chats",
            onClick = { onNavigate("chats") }
        )
    }
}
