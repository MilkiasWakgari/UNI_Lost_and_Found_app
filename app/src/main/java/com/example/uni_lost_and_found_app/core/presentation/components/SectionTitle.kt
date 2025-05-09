package com.example.uni_lost_and_found_app.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uni_lost_and_found_app.R

@Composable
fun SectionTitle(title: String) {
    val customFontFamily = FontFamily(
        Font(R.font.plus_jakarta_sans_medium)
    )
    Text(text = title,
        fontSize = 20.sp,
        fontWeight = FontWeight.W900,
        style = MaterialTheme.typography.headlineSmall.copy(fontFamily = customFontFamily)
    )
    Spacer(modifier = Modifier.height(8.dp))
}
