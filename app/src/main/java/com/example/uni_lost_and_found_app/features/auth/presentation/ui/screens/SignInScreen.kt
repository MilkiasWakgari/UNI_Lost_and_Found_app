package com.example.uni_lost_and_found_app.features.auth.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uni_lost_and_found_app.R
import com.example.uni_lost_and_found_app.core.presentation.components.SignInButton
import androidx.navigation.NavController

@Composable
fun SignInScreen(
    onBack: () -> Unit,
    onForgotPassword: () -> Unit,
    onSignInSuccess: () -> Unit,
    navController: NavController
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    val customFontFamily = FontFamily(
        Font(R.font.plus_jakarta_sans_medium)
    )
    val isEmailValid = email.contains("@") && email.contains(".")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp)
    ) {
        IconButton(
            onClick = onBack,
            modifier = Modifier.align(Alignment.Start)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back"
            )
        }
        
        Spacer(Modifier.height(42.dp))
        Text(
            text = stringResource(id = R.string.sign_in_title),
            color = colorResource(id = R.color.deep_sky_blue),
            fontWeight = FontWeight.Bold,
            fontSize = 42.sp,
            modifier = Modifier.padding(bottom = 4.dp),
            style = MaterialTheme.typography.headlineLarge.copy(fontFamily = customFontFamily)
        )
        Spacer(Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.sign_in_subtitle),
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 24.dp),
            style = MaterialTheme.typography.bodyMedium.copy(fontFamily = customFontFamily)
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(stringResource(id = R.string.sign_in_email_hint)) },
            colors = TextFieldDefaults.colors(
                disabledContainerColor = colorResource(id = R.color.white_smoke)
            ),
            singleLine = true,
            trailingIcon = {
                if (isEmailValid) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = "Valid",
                        tint = colorResource(id = R.color.success)
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(stringResource(id = R.string.sign_in_password_hint)) },
            colors = TextFieldDefaults.colors(
                disabledContainerColor = colorResource(id = R.color.white_smoke)
            ),
            singleLine = true,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val icon = if (passwordVisible) Icons.Filled.VisibilityOff else Icons.Filled.Visibility
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = icon, contentDescription = "Toggle Password Visibility")
                }
            },
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = stringResource(id = R.string.forgot_password),
                color = colorResource(id = R.color.deep_sky_blue),
                modifier = Modifier.clickable(onClick = onForgotPassword)
            )
        }
        Spacer(Modifier.height(24.dp))
        SignInButton(
            text = stringResource(id = R.string.sign_in),
            onClick = {
                // Simulate login and check for admin role
                if (email == "admin@example.com") {
                    navController.navigate("admin_dashboard")
                } else {
                    onSignInSuccess()
                }
            }
        )
    }
}
