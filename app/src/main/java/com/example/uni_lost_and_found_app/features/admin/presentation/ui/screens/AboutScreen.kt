package com.example.uni_lost_and_found_app.ui.screens.home

import android.graphics.Paint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uni_lost_and_found_app.R

data class Member(val name: String, val id: String, val section: String)

@Composable
fun MembersScreen() {
    val customFontFamily = FontFamily(
        Font(R.font.plus_jakarta_sans_medium)
    )

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .padding(top = 16.dp, bottom = 16.dp, start = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "UNI LOST & FOUND",
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    modifier = Modifier.padding(bottom = 4.dp),
                    style = MaterialTheme.typography.bodyMedium.copy(fontFamily = customFontFamily)
                )
                Text(
                    text = "DESIGNED BY:",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    style = MaterialTheme.typography.bodyMedium.copy(fontFamily = customFontFamily)
                )
                Spacer(Modifier.height(10.dp))
                val membersList = listOf(
                    Member("ABUBEKER JUHAR", "UGR/3857/15", "SECTION 3"),
                    Member("DAGMAWI MINALE", "UGR/8048/14", "SECTION 3"),
                    Member("EYOSIYAS BISRAT", "UGR/2434/15", "SECTION 3"),
                    Member("FIKIR TILAHUN", "UGR/0163/15", "SECTION 3"),
                    Member("MILKIAS WAKGARI", "UGR/0422/15", "SECTION 3")
                )

                LazyColumn(
                    contentPadding = PaddingValues(vertical = 4.dp),
                    verticalArrangement = Arrangement.spacedBy(30.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(membersList) { member ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = member.name,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.weight(1.5f)
                            )
                            Text(
                                text = member.id,
                                fontSize = 13.sp,
                                modifier = Modifier.weight(1f)
                            )
                            Text(
                                text = member.section,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }
            }
        }
    }
}
