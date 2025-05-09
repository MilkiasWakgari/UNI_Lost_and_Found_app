package com.example.uni_lost_and_found_app.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ItemSelection(
    todayItems: List<Pair<String, String>>,
    last7DaysItems: List<Triple<String, String, String>>
) {
    SectionTitle(title = "Today")

    LazyRow {
        items(todayItems) { (title, time) ->
            ItemCard(title = title, subtitle = time, modifier = Modifier.padding(end = 8.dp))
        }
    }

    Spacer(modifier = Modifier.height(24.dp))
    SectionTitle(title = "Last 7 Days")

    LazyColumn {
        items(last7DaysItems) { (title, date, time) ->
            ItemCard(
                title = title,
                subtitle = "$date\n$time",
                modifier = Modifier.padding(vertical = 6.dp)
            )
        }
    }
}
