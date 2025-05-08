package com.example.uni_lost_and_found_app.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.uni_lost_and_found_app.ui.components.BottomNavigationBar
import com.example.uni_lost_and_found_app.ui.components.CustomTopAppBar
import com.example.uni_lost_and_found_app.ui.components.ItemSelection

@Composable
fun ItemScreen(title: String) {
    val todayItems = listOf("Bicycle" to "11:23", "Laptop" to "11:25")
    val last7DaysItems = listOf(
        Triple("Road Bike", "Mar 10, 2023", "23:44"),
        Triple("Helmet", "Mar 3, 2023", "10:45"),
        Triple("Water Bottle", "Mar 1, 2023", "14:00"),
        Triple("Mountain Bike", "Feb 27, 2023", "16:20")
    )

    Scaffold(
        topBar = { CustomTopAppBar(title = title) },
        bottomBar = { BottomNavigationBar() }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            ItemSelection(todayItems = todayItems, last7DaysItems = last7DaysItems)
        }
    }
}
