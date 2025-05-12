package com.example.uni_lost_and_found_app.features.item.data.model

data class ItemDto(
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val category: String = "",
    val location: String = "",
    val date: String = "", // Use String for network transfer
    val imageUrl: String = "",
    val status: String = "",
    val userId: String = ""
) 