package com.example.uni_lost_and_found_app.ui.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uni_lost_and_found_app.R

@Composable
fun EnterCodeScreen(
    onConfirmClick: () -> Unit = {},
    onCancelClick: () -> Unit = {}
) {
    val customFontFamily = FontFamily(Font(R.font.plus_jakarta_sans_medium))

    var code1 by remember { mutableStateOf("") }
    var code2 by remember { mutableStateOf("") }
    var code3 by remember { mutableStateOf("") }
    var code4 by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Card(
            shape = RoundedCornerShape(8.dp),
            elevation = CardDefaults.cardElevation(6.dp),
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(1f),
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
                    .background(Color.White)
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Enter 4 digit code",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "A four-digit code should have come to your email address that you indicated.",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center,
                    fontFamily = customFontFamily
                )
                Spacer(modifier = Modifier.height(22.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(25.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CodeInputField(value = code1) { code1 = it }
                    CodeInputField(value = code2) { code2 = it }
                    CodeInputField(value = code3) { code3 = it }
                    CodeInputField(value = code4) { code4 = it }
                }
                Spacer(modifier = Modifier.height(30.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Button(
                        onClick = onConfirmClick,
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF2D3648)
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Confirm", color = Color.White)
                    }
                    OutlinedButton(
                        onClick = onCancelClick,
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Cancel")
                    }
                }
            }
        }
    }
}

@Composable
fun CodeInputField(value: String, onValueChange: (String) -> Unit) {
    TextField(
        value = value,
        onValueChange = { if (it.length <= 1) onValueChange(it) },
        modifier = Modifier
            .width(50.dp)
            .height(80.dp),
        singleLine = true,
        textStyle = LocalTextStyle.current.copy(
            textAlign = TextAlign.Center,
            fontSize = 24.sp
        ),
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color(0xFFF5F5F5),
            unfocusedContainerColor = Color(0xFFF5F5F5),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(8.dp)
    )
}
