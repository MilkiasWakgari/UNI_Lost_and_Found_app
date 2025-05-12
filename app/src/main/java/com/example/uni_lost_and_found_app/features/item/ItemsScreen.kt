package com.example.uni_lost_and_found_app.features.item

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.uni_lost_and_found_app.core.presentation.components.BottomNavigationBar
import com.example.uni_lost_and_found_app.core.presentation.components.CustomTopAppBar
import com.example.uni_lost_and_found_app.core.presentation.components.ItemSelection
import com.example.uni_lost_and_found_app.features.item.data.repository.ItemRepository
import com.example.uni_lost_and_found_app.features.item.domain.model.Item
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ItemsScreen(
    title: String,
    currentRoute: String,
    itemRepository: ItemRepository,
    onNavigate: (String) -> Unit
) {
    var todayItems by remember { mutableStateOf<List<Item>>(emptyList()) }
    var last7DaysItems by remember { mutableStateOf<List<Item>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf<String?>(null) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        scope.launch {
            try {
                val todayResult = itemRepository.getTodayItems()
                val recentResult = itemRepository.getRecentItems()
                
                todayResult.onSuccess { items ->
                    todayItems = items
                }.onFailure { e ->
                    error = e.message
                }

                recentResult.onSuccess { items ->
                    last7DaysItems = items
                }.onFailure { e ->
                    error = e.message
                }
            } catch (e: Exception) {
                error = e.message
            } finally {
                isLoading = false
            }
        }
    }

    Scaffold(
        topBar = { 
            CustomTopAppBar(
                title = title,
                onBackClick = { onNavigate("welcome") }
            ) 
        },
        bottomBar = { 
            BottomNavigationBar(
                currentRoute = currentRoute,
                onNavigate = onNavigate
            ) 
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            } else if (error != null) {
                Text(
                    text = error!!,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            } else {
                ItemSelection(
                    todayItems = todayItems.map { it.title to SimpleDateFormat("HH:mm", Locale.getDefault()).format(it.date) },
                    last7DaysItems = last7DaysItems.map { 
                        Triple(
                            it.title,
                            SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(it.date),
                            SimpleDateFormat("HH:mm", Locale.getDefault()).format(it.date)
                        )
                    }
                )
            }
        }
    }
}
