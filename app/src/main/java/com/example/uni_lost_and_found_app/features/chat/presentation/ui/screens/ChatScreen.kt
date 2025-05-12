package com.example.uni_lost_and_found_app.features.chat.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uni_lost_and_found_app.R
import com.example.uni_lost_and_found_app.core.presentation.components.BottomNavigationBar
import com.example.uni_lost_and_found_app.core.presentation.components.CustomTopAppBar

@Composable
fun ChatScreen(
    onBack: () -> Unit = {},
    currentRoute: String = "",
    onNavigate: (String) -> Unit = {}
) {
    val messages = listOf(
        Triple("Kevin", "Hey, I found your notebook!", "7:01"),
        Triple("Kevin", "You can collect it at the lost and found office.", "7:03"),
        Triple("Kevin", "Bring your ID please.", "7:05")
    )

    Scaffold(
        topBar = { CustomTopAppBar(title = "Messages", onBackClick = onBack) },
        bottomBar = { BottomNavigationBar(currentRoute = currentRoute, onNavigate = onNavigate) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            messages.forEach { (name, message, time) ->
                MessageItem(name = name, message = message, time = time)
            }
        }
    }
}

@Composable
fun MessageItem(name: String, message: String, time: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.image3),
            contentDescription = "Profile Image",
            modifier = Modifier.size(50.dp)
        )

        Column(
            modifier = Modifier
                .padding(start = 16.dp)
                .weight(1f)
        ) {
            Text(
                text = name,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = Color.Black
            )
            Text(
                text = message,
                fontSize = 14.sp,
                color = Color(0xFF686E7C)
            )
        }

        Text(
            text = time,
            fontSize = 14.sp,
            color = Color(0xFF686E7C),
            modifier = Modifier.padding(start = 4.dp)
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun ChatScreenPreview() {
    ChatScreen()
}
