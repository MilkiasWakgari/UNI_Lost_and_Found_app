package com.example.uni_lost_and_found_app.features.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uni_lost_and_found_app.R
import com.example.uni_lost_and_found_app.core.presentation.components.BottomNavigationBar
import com.example.uni_lost_and_found_app.core.presentation.components.CustomTopAppBar

@Composable
fun ReportItemFoundScreen() {
    Scaffold(
        topBar = { CustomTopAppBar(title = "REPORT ITEM") },
        bottomBar = { BottomNavigationBar() }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                FoundLostToggle(isFoundSelected = true)
            }

            Spacer(modifier = Modifier.height(32.dp))

            InputField(label = "Item Name", value = "Bag")
            Spacer(modifier = Modifier.height(16.dp))

            InputField(label = "Location", value = "Room 211")
            Spacer(modifier = Modifier.height(16.dp))

            Text("Time", fontSize = 12.sp, color = Color.Black)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 4.dp)
            ) {
                Text(
                    text = "04 APR 2025",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = (-0.3).sp
                )
                Spacer(modifier = Modifier.width(26.dp))
                Text(
                    text = "7:01 AM",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = (-0.3).sp
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            InputField(label = "Description", value = "-")
            Spacer(modifier = Modifier.height(16.dp))

            Text("Picture", fontSize = 12.sp, color = Color.Black)
            Spacer(modifier = Modifier.height(8.dp))
            Image(
                painter = painterResource(id = R.drawable.image2),
                contentDescription = "Item Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(12.dp))
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ActionButton(text = "Cancel", backgroundColor = Color.Gray)
                ActionButton(text = "Confirm", backgroundColor = Color(0xff124a7d))
            }
        }
    }
}

@Composable
fun InputField(label: String, value: String) {
    Column {
        Text(label, fontSize = 12.sp, color = Color.Black)
        Box(
            modifier = Modifier
                .padding(top = 4.dp)
                .fillMaxWidth()
                .height(36.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color(0xffd9d9d9))
                .border(1.dp, Color.Black, RoundedCornerShape(10.dp))
                .padding(horizontal = 8.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(value, fontSize = 12.sp, fontWeight = FontWeight.Medium)
        }
    }
}

@Composable
fun ActionButton(text: String, backgroundColor: Color) {
    Box(
        modifier = Modifier
            .width(150.dp)
            .height(50.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Text(text, color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Medium)
    }
}

@Composable
fun FoundLostToggle(isFoundSelected: Boolean) {
    val selectedColor = Color(0xff2f2f2f)
    val unselectedColor = Color(0xffd9d9d9)

    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        Box(
            modifier = Modifier
                .width(136.dp)
                .height(54.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(if (isFoundSelected) selectedColor else unselectedColor),
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
                .background(if (!isFoundSelected) selectedColor else unselectedColor),
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

