package com.example.uni_lost_and_found_app.ui.screens.item

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.uni_lost_and_found_app.core.presentation.components.*

@Composable
fun ItemsFoundScreen() {
    val todayItems = listOf(
        "Bicycle" to "11:23",
        "Laptop" to "11:25",
        "Phone" to "09:10",
        "Umbrella" to "13:47",
        "Notebook" to "15:05"
    )

    val last7DaysItems = listOf(
        Triple("Road Bike", "Mar 10, 2023", "23:44"),
        Triple("Helmet", "Mar 3, 2023", "10:45"),
        Triple("Water Bottle", "Mar 1, 2023", "14:00"),
        Triple("Mountain Bike", "Feb 27, 2023", "16:20"),
        Triple("Backpack", "Feb 26, 2023", "18:30"),
        Triple("Glasses", "Feb 25, 2023", "08:15"),
        Triple("ID Card", "Feb 24, 2023", "09:05"),
        Triple("Smartwatch", "Feb 23, 2023", "10:00"),
        Triple("Camera", "Feb 22, 2023", "13:50"),
        Triple("USB Drive", "Feb 21, 2023", "16:45"),
        Triple("Keys", "Feb 20, 2023", "12:30"),
        Triple("Shoes", "Feb 19, 2023", "17:10")
    )

    Scaffold(
        topBar = { CustomTopAppBar(title = "ITEMS FOUND") },
        bottomBar = { BottomNavigationBar() }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .fillMaxSize()
        ) {
            SectionTitle(title = "Today")
            Spacer(modifier = Modifier.height(8.dp))

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(todayItems) { (title, time) ->
                    ItemCard(
                        title = title,
                        subtitle = time,
                        modifier = Modifier.width(160.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
            SectionTitle(title = "Last 7 Days")
            Spacer(modifier = Modifier.height(8.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(bottom = 100.dp)
            ) {
                items(last7DaysItems) { (title, date, time) ->
                    ItemCard(
                        title = title,
                        subtitle = "$date\n$time",
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}
