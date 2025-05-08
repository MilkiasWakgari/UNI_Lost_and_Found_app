package com.example.uni_lost_and_found_app.ui.components

import android.graphics.Color

data class ItemData(
    val imageRes: Int,
    val title: String,
    val subtitle: String,
    val date: String,
    val time: String,
    val backgroundColor: Color
)

data class FoundItem(
    val title: String,
    val date: String,
    val time: String
)
