package com.example.uni_lost_and_found_app.features.profile.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
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
fun DeleteAccount(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = { CustomTopAppBar(title = "DELETE ACCOUNT") },
        bottomBar = { BottomNavigationBar() }
    ) { padding ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 32.dp, vertical = 24.dp)
            ) {
                // Profile Image and Info
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 40.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.image3),
                        contentDescription = "Profile Picture",
                        modifier = Modifier.size(100.dp)
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    Column {
                        Text(
                            text = "deez",
                            color = Color.Black,
                            style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Medium)
                        )
                        Text(
                            text = "deez@gmail.com",
                            color = Color.Black,
                            style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.Medium)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(60.dp))

                // Delete Prompt
                Text(
                    text = "Are you sure you want to delete your account?",
                    color = Color.Black,
                    style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Medium),
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(60.dp))

                // Cancel Button
                TextButton(
                    onClick = { /* Handle cancel */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .size(width = 176.dp, height = 39.dp)
                        .background(color = Color(0xFFD9D9D9), shape = MaterialTheme.shapes.small)
                ) {
                    Text(
                        text = "CANCEL",
                        color = Color.Black,
                        style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.Medium),
                        modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center)
                    )
                }

                Spacer(modifier = Modifier.height(30.dp))

                // Delete Button
                TextButton(
                    onClick = { /* Handle delete */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .size(width = 176.dp, height = 39.dp)
                        .background(color = Color(0xFFD9D9D9), shape = MaterialTheme.shapes.small)
                ) {
                    Text(
                        text = "Delete Account",
                        color = Color.Red,
                        style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.Medium),
                        modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center)
                    )
                }
            }
        }
    }
}

@Preview(widthDp = 393, heightDp = 852)
@Composable
private fun DeleteAccountPreview() {
    DeleteAccount()
}
