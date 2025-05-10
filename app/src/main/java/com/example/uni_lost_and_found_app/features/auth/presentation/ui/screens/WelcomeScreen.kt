package com.example.uni_lost_and_found_app.features.auth.presentation.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uni_lost_and_found_app.R
import com.example.uni_lost_and_found_app.core.presentation.components.SignInButton

@Composable
fun WelcomeScreen(
    onSignInClick: () -> Unit = {},
    onSignUpClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 134.dp, start = 20.dp, end = 20.dp)
    ) {
        val customFontFamily = FontFamily(
            Font(R.font.plus_jakarta_sans_medium) // Updated to use the correct font file
        )
        Text(
            text = stringResource(id = R.string.welcome_title),
            fontSize = 42.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.deep_sky_blue),
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.headlineLarge.copy(fontFamily = customFontFamily)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.welcome_subtitle),
            modifier = Modifier.fillMaxWidth(),
            fontSize = 16.sp,
            style = MaterialTheme.typography.titleSmall.copy(fontFamily = customFontFamily)
        )
        Spacer(Modifier.height(120.dp))
        Text(
            text = stringResource(id = R.string.welcome_prompt),
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.bodyMedium.copy(fontFamily = customFontFamily)
        )
        Spacer(Modifier.height(13.dp))
        SignInButton(
            text = stringResource(id = R.string.sign_in_button),
            onClick = onSignInClick
        )
        Spacer(Modifier.height(27.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = stringResource(id = R.string.welcome_new_customer),
                style = MaterialTheme.typography.bodyMedium.copy(fontFamily = customFontFamily)
            )
            Text(
                text = stringResource(id = R.string.welcome_create_new_account),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyMedium.copy(fontFamily = customFontFamily),
                modifier = Modifier.clickable(onClick = onSignUpClick)
            )
        }
    }
}

