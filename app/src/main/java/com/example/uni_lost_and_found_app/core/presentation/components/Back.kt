package com.example.uni_lost_and_found_app.core.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Back(onBackClick: () -> Unit = {}) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable { onBackClick() }
    ) {
        val customFontFamily = FontFamily(
            Font(com.example.uni_lost_and_found_app.R.font.plus_jakarta_sans_medium)
        )
        Icon(
            imageVector = Icons.Outlined.KeyboardArrowLeft,
            contentDescription = "Back arrow"
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = "Back",
            fontSize = 16.sp,
            style = MaterialTheme.typography.bodyMedium.copy(fontFamily = customFontFamily)
        )
    }
}