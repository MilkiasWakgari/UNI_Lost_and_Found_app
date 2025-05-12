package com.example.uni_lost_and_found_app.features.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ItemDetailsFound(modifier: Modifier = Modifier) {
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

        Icon(
            painter = painterResource(id = R.drawable.arrow_back),
            contentDescription = "arrow_back",
            tint = Color.White,
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 31.dp,
                    y = 52.dp))

        Text(
            text = "ITEM DETAILS",
            color = Color.White,
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold),
            modifier = Modifier
                .align(alignment = Alignment.TopCenter)
                .offset(x = (-1).dp,
                    y = 52.dp))
        Image(
            painter = painterResource(id = R.drawable.electric20bicyclei052),
            contentDescription = "Electric%20Bicycle.I05 2",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 2.dp,
                    y = 104.dp)
                .requiredWidth(width = 387.dp)
                .requiredHeight(height = 187.dp))

        Text(
            text = "ITEM NAME",
            color = Color.Black,
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold),
            modifier = Modifier
                .align(alignment = Alignment.TopCenter)
                .offset(x = (-100).dp,
                    y = 354.dp)
                .requiredWidth(width = 137.dp))

        Text(
            text = "FOUND AT",
            color = Color.Black,
            style = TextStyle(
                fontSize = 15.sp,
                letterSpacing = (-0.3).sp),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 209.dp,
                    y = 462.dp)
                .requiredWidth(width = 101.dp)
                .requiredHeight(height = 16.dp))

        Text(
            text = "FOUND ON",
            color = Color.Black,
            style = TextStyle(
                fontSize = 15.sp,
                letterSpacing = (-0.3).sp),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 28.dp,
                    y = 462.dp)
                .requiredWidth(width = 101.dp)
                .requiredHeight(height = 16.dp))

        Text(text = "7:01 AM",
            color = Color.Black,
            style = TextStyle(
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = (-0.3).sp),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 216.dp,
                    y = 495.dp)
                .requiredWidth(width = 94.dp)
                .requiredHeight(height = 20.dp))

        Text(
            text = "04 APR 2025",
            color = Color.Black,
            style = TextStyle(
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = (-0.3).sp),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 31.dp,
                    y = 495.dp)
                .requiredWidth(width = 94.dp)
                .requiredHeight(height = 20.dp))

        Text(
            text = "Description",
            color = Color.Black,
            style = TextStyle(
                fontSize = 15.sp,
                letterSpacing = (-0.3).sp),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 31.dp,
                    y = 551.dp)
                .requiredWidth(width = 101.dp)
                .requiredHeight(height = 16.dp))

        Text(
            text = "Location",
            color = Color.Black,
            style = TextStyle(
                fontSize = 15.sp,
                letterSpacing = (-0.3).sp),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 205.dp,
                    y = 551.dp)
                .requiredWidth(width = 101.dp)
                .requiredHeight(height = 16.dp))

        Text(
            text = "NONE.",
            color = Color.Black,
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold),
            modifier = Modifier
                .align(alignment = Alignment.TopCenter)
                .offset(x = (-97).dp,
                    y = 575.dp)
                .requiredWidth(width = 137.dp))

        Text(
            text = "Lounge",
            color = Color.Black,
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold),
            modifier = Modifier
                .align(alignment = Alignment.TopCenter)
                .offset(x = 77.dp,
                    y = 575.dp)
                .requiredWidth(width = 137.dp))

        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 92.dp,
                    y = 643.dp)
                .requiredWidth(width = 207.dp)
                .requiredHeight(height = 51.dp)
        ) {

            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopCenter)
                    .offset(x = 0.dp,
                        y = 0.dp)
                    .requiredWidth(width = 207.dp)
                    .requiredHeight(height = 51.dp)
                    .clip(shape = RoundedCornerShape(15.dp))
                    .background(color = Color(0xff979797)))

            Text(
                text = "CLAIM",
                color = Color.Black,
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold),
                modifier = Modifier
                    .align(alignment = Alignment.Center)
                    .offset(x = 0.dp,
                        y = (-0.5).dp))
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
                    .requiredWidth(width = 393.dp).requiredHeight(height = 63.dp)
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
                        colorFilter = ColorFilter.tint(Color(0xff2f2f2f)),
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
                            .requiredSize(size = 24.dp)

                    Image(
                        painter = painterResource(id = R.drawable.materialsymbolslightadd),
                        contentDescription = "material-symbols-light:add",
                        colorFilter = ColorFilter.tint(Color.White),
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 175.dp,
                                y = 23.dp)
                            .requiredSize(size = 24.dp))

                    Image(
                        painter = painterResource(id = R.drawable.streamlinelostandfound),
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
private fun ItemDetailsFoundPreview() {
    ItemDetailsFound(Modifier)
}