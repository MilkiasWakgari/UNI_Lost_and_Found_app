package com.example.uni_lost_and_found_app.features.item.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.uni_lost_and_found_app.core.presentation.components.ItemCard
import com.example.uni_lost_and_found_app.core.presentation.components.CustomTopAppBar
import com.example.uni_lost_and_found_app.core.presentation.components.BottomNavigationBar
import com.example.uni_lost_and_found_app.core.presentation.components.SectionTitle
import com.example.uni_lost_and_found_app.features.item.domain.model.Item
import com.example.uni_lost_and_found_app.features.item.data.repository.ItemRepository
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import com.example.uni_lost_and_found_app.features.item.presentation.ui.ItemsScreen

@Composable
fun ItemsLostScreen(
    itemRepository: ItemRepository,
    onNavigate: (String) -> Unit
) {
    ItemsScreen(
        title = "ITEMS LOST",
        currentRoute = "items_lost",
        itemRepository = itemRepository,
        onNavigate = onNavigate
    )
}
