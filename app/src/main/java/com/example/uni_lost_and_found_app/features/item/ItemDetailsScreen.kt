package com.example.uni_lost_and_found_app.features.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uni_lost_and_found_app.R
import com.example.uni_lost_and_found_app.core.presentation.components.BottomNavigationBar
import com.example.uni_lost_and_found_app.core.presentation.components.CustomTopAppBar

@Composable
fun ItemDetailsFoundScreen() {
    Scaffold(
        topBar = { CustomTopAppBar(title = "ITEM DETAILS") },
        bottomBar = { BottomNavigationBar() }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color.White)
        ) {
            // Image banner
            Image(
                painter = painterResource(id = R.drawable.electric20bicyclei052),
                contentDescription = "Found Item Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Item name
            Text(
                text = "ITEM NAME",
                color = Color.Black,
                style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Date & Time Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(text = "FOUND ON", fontSize = 15.sp, color = Color.Black)
                    Text(text = "04 APR 2025", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                }
                Column {
                    Text(text = "FOUND AT", fontSize = 15.sp, color = Color.Black)
                    Text(text = "7:01 AM", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Description & Location Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(text = "Description", fontSize = 15.sp, color = Color.Black)
                    Text(text = "NONE.", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                }
                Column {
                    Text(text = "Location", fontSize = 15.sp, color = Color.Black)
                    Text(text = "Lounge", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            // Claim button
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clip(RoundedCornerShape(15.dp))
                    .background(Color(0xFF979797))
                    .padding(horizontal = 32.dp)
                    .height(51.dp)
                    .width(207.dp)
            ) {
                Text(
                    text = "CLAIM",
                    color = Color.Black,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
