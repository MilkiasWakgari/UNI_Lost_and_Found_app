package com.example.uni_lost_and_found_app.ui.screens.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uni_lost_and_found_app.R
import com.example.uni_lost_and_found_app.ui.components.Back
import com.example.uni_lost_and_found_app.ui.components.SignInButton

@Composable
fun ForgotPasswordScreen() {
    var email by remember { mutableStateOf("") }
    val customFontFamily = FontFamily(
        Font(R.font.plus_jakarta_sans_medium)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .padding(top = 20.dp)
    ) {
        Back()
        Spacer(modifier = Modifier.height(42.dp))
        Text(
            text = stringResource(id = R.string.forgot_password),
            fontSize = 42.sp,
            color = colorResource(id = R.color.deep_sky_blue),
            style = MaterialTheme.typography.headlineLarge.copy(fontFamily = customFontFamily),
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.forgot_password_subtitle),
            fontSize = 16.sp,
            style = MaterialTheme.typography.bodyMedium.copy(fontFamily = customFontFamily)
        )
        Spacer(modifier = Modifier.height(86.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(
                text = "Email",
                style = MaterialTheme.typography.bodyMedium.copy(fontFamily = customFontFamily),
                ) },
            colors = TextFieldDefaults.colors(
                disabledContainerColor = colorResource(id = R.color.white_smoke)
            ),
            placeholder = { Text(
                text ="Enter email",
                style = MaterialTheme.typography.bodyMedium.copy(fontFamily = customFontFamily),
                fontStyle = FontStyle.Italic
            ) },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(32.dp))
        SignInButton (
            text = "Continue",
            onClick = {//TODO
            // }
            },
        )
    }
}
