package com.example.uni_lost_and_found_app.core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight

@Composable
fun FoundLostToggle(
    isFoundSelected: Boolean,
    onFoundClick: () -> Unit,
    onLostClick: () -> Unit
) {
    val selectedColor = Color(0xff2f2f2f)
    val unselectedColor = Color(0xffd9d9d9)

    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        Box(
            modifier = Modifier
                .width(136.dp)
                .height(54.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(if (isFoundSelected) selectedColor else unselectedColor)
                .clickable(onClick = onFoundClick),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Found Item",
                color = if (isFoundSelected) Color.White else Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Box(
            modifier = Modifier
                .width(136.dp)
                .height(54.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(if (!isFoundSelected) selectedColor else unselectedColor)
                .clickable(onClick = onLostClick),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Lost Item",
                color = if (!isFoundSelected) Color.White else Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}
