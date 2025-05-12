package com.example.uni_lost_and_found_app.features.item.data.model

import java.util.Date

data class Item(
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val category: String = "",
    val location: String = "",
    val date: Date = Date(),
    val imageUrl: String = "",
    val status: ItemStatus = ItemStatus.lost,
    val userId: String = ""
)

enum class ItemStatus {
    lost,
    found,
    claimed
} 