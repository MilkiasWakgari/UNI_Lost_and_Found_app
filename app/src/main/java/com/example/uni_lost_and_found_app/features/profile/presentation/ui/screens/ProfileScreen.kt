package com.example.uni_lost_and_found_app.features.profile.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
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
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.uni_lost_and_found_app.R
import com.example.uni_lost_and_found_app.core.presentation.components.BottomNavigationBar
import com.example.uni_lost_and_found_app.core.presentation.components.CustomTopAppBar

@Composable
fun ProfilePage(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = { CustomTopAppBar(title = "SETTINGS") },
        bottomBar = { BottomNavigationBar() }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(bottom = 12.dp) // Optional extra padding above bottom bar
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(start = 56.dp, top = 29.dp)
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

            Spacer(modifier = Modifier.height(40.dp))

            ProfileOption(
                icon = { Icon(Icons.Default.Person, contentDescription = "My Profile") },
                text = "My Profile"
            )

            Spacer(modifier = Modifier.height(20.dp))

            ProfileOption(
                icon = { Icon(Icons.Default.List, contentDescription = "My Items") },
                text = "My Items"
            )

            Spacer(modifier = Modifier.height(20.dp))

            ProfileOption(
                icon = { Icon(Icons.Default.ExitToApp, contentDescription = "Log Out") },
                text = "Log Out"
            )

            Spacer(modifier = Modifier.height(20.dp))

            ProfileOption(
                icon = { Icon(Icons.Default.Delete, contentDescription = "Delete Account") },
                text = "Delete Account"
            )
        }
    }
}

@Composable
fun ProfileOption(
    icon: @Composable () -> Unit,
    text: String
) {
    Box(
        modifier = Modifier
            .padding(horizontal = 44.dp)
            .fillMaxWidth()
            .height(30.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxSize()
        ) {
            icon()
            Spacer(modifier = Modifier.width(25.dp))
            Text(
                text = text,
                color = Color.Black,
                lineHeight = 1.33.em,
                style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.Medium)
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                Icons.Default.KeyboardArrowRight,
                contentDescription = "Chevron Right",
                modifier = Modifier.padding(end = 8.dp)
            )
        }
    }
}

@Preview(widthDp = 393, heightDp = 852)
@Composable
private fun ProfilePagePreview() {
    ProfilePage()
}
