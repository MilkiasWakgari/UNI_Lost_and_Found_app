package com.example.uni_lost_and_found_app.features.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.uni_lost_and_found_app.R
import com.example.uni_lost_and_found_app.core.presentation.components.BottomNavigationBar
import com.example.uni_lost_and_found_app.core.presentation.components.CustomTopAppBar
import com.example.uni_lost_and_found_app.core.presentation.components.FoundLostToggle
import com.example.uni_lost_and_found_app.core.presentation.components.InputField
import com.example.uni_lost_and_found_app.core.presentation.components.ActionButton

@Composable
fun ReportItemFoundScreen(navController: NavController) {
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
            FoundLostToggle(isFoundSelected = true, onFoundClick = {}, onLostClick = {
                navController.navigate("reportItemLost") {
                    popUpTo("reportItemFound") { inclusive = true }
                }
            })

            Spacer(modifier = Modifier.height(32.dp))

            InputField(label = "Item Name", value = "Bag")
            Spacer(modifier = Modifier.height(16.dp))

            InputField(label = "Location", value = "Room 211")
            Spacer(modifier = Modifier.height(16.dp))

            Text("Time", fontSize = 12.sp, color = Color.Black)
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 4.dp)) {
                Text("04 APR 2025", fontSize = 15.sp, fontWeight = FontWeight.Bold, letterSpacing = (-0.3).sp)
                Spacer(modifier = Modifier.width(26.dp))
                Text("7:01 AM", fontSize = 15.sp, fontWeight = FontWeight.Bold, letterSpacing = (-0.3).sp)
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
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                ActionButton(text = "Cancel", backgroundColor = Color.Gray)
                ActionButton(text = "Confirm", backgroundColor = Color(0xff124a7d))
            }
        }
    }
}
