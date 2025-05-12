package com.example.uni_lost_and_found_app.features.profile.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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

@Composable
fun DeleteAccount(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .requiredWidth(width = 393.dp)
            .requiredHeight(height = 852.dp)
            .background(color = Color.White)
    ) {

        Image(
            painter = painterResource(id = R.drawable.rectangle175),
            contentDescription = "Rectangle 175",
            modifier = Modifier
                .requiredWidth(width = 393.dp)
                .requiredHeight(height = 100.dp))

        Text(
            text = "DELETE ACCOUNT",
            color = Color.White,
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold),
            modifier = Modifier
                .align(alignment = Alignment.TopCenter)
                .offset(x = 0.dp,
                    y = 52.dp))

        Image(
            painter = painterResource(id = R.drawable.image3),
            contentDescription = "image 3",
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 56.dp,
                    y = 129.dp)
                .requiredSize(size = 100.dp))

        Text(
            text = "deez",
            color = Color.Black,
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 177.dp,
                    y = 156.dp))

        Text(
            text = "deez@gmail.com",
            color = Color.Black,
            style = TextStyle(
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 177.dp,
                    y = 196.dp))

        Text(
            text = "Are you sure you want to delete your account?",
            color = Color.Black,
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium),
            modifier = Modifier
                .align(alignment = Alignment.TopCenter)
                .offset(x = 0.5.dp,
                    y = 299.dp)
                .requiredWidth(width = 238.dp))

        TextButton(
            onClick = { /* Handle cancel action */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 105.dp, y = 433.dp)
                .size(width = 176.dp, height = 39.dp)
                .background(color = Color(0xFFD9D9D9), shape = MaterialTheme.shapes.small)
        ) {

            Text(
                text = "CANCEL",
                color = Color.Black,style = TextStyle(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium
                ),
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            )
        }

        TextButton(
            onClick = { /* Handle delete account action */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 106.dp, y = 501.dp)
                .size(width = 176.dp, height = 39.dp)
                .background(color = Color(0xFFD9D9D9), shape = MaterialTheme.shapes.small)
        ) {

            Text(
                text = "Delete Account",
                color = Color.Red,
                style = TextStyle(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium
                ),
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            )
        }

        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 0.dp,
                    y = 789.dp)
                .requiredWidth(width = 393.dp)
                .requiredHeight(height = 63.dp)
        ) {

            Box(
                modifier = Modifier
                    .requiredWidth(width = 393.dp)
                    .requiredHeight(height = 63.dp)
                    .background(color = Color(0xff356da0)))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .requiredWidth(width = 393.dp)
                    .requiredHeight(height = 49.dp)

            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.materialsymbolslightsearch),
                        contentDescription = "material-symbols-light:search",
                        colorFilter = ColorFilter.tint(Color.White),
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 27.dp,
                                y = 23.dp)
                            .requiredSize(size = 24.dp))

                    Image(
                        painter = painterResource(id = R.drawable.iconamoonprofilethin),
                        contentDescription = "iconamoon:profile-thin",
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 333.dp,
                                y = 23.dp)
                            .requiredSize(size = 24.dp))

                    Image(
                        painter = painterResource(id = R.drawable.materialsymbolslightchatoutline),
                        contentDescription = "material-symbols-light:chat-outline",
                        colorFilter = ColorFilter.tint(Color.White),
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 254.dp,
                                y = 23.dp)
                            .requiredSize(size = 24.dp))

                    Image(
                        painter = painterResource(id = R.drawable.materialsymbolslightadd),
                        contentDescription = "material-symbols-light:add",
                        colorFilter = ColorFilter.tint(Color.White),
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 175.dp,
                                y = 23.dp)
                            .requiredSize(size = 24.dp))

                    Image(painter = painterResource(id = R.drawable.streamlinelostandfound),
                        contentDescription = "streamline:lost-and-found",
                        modifier = Modifier
                            .align(alignment = Alignment.CenterStart)
                            .offset(x = 101.dp,
                                y = 10.5.dp)
                            .requiredSize(size = 24.dp))
                }
            }
        }
    }
}

@Preview(widthDp = 393, heightDp = 852)
@Composable
private fun DeleteAccountPreview() {
    DeleteAccount(Modifier)
}