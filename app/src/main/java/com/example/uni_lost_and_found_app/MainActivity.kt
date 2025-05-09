package com.example.uni_lost_and_found_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.uni_lost_and_found_app.ui.screens.auth.EnterCodeScreen
import com.example.uni_lost_and_found_app.ui.screens.auth.ForgotPasswordScreen
import com.example.uni_lost_and_found_app.ui.screens.auth.SignInScreen
import com.example.uni_lost_and_found_app.ui.screens.auth.SignUpScreen
import com.example.uni_lost_and_found_app.ui.screens.auth.WelcomeScreen
import com.example.uni_lost_and_found_app.ui.screens.home.MembersScreen
import com.example.uni_lost_and_found_app.ui.screens.item.ItemsFoundScreen
import com.example.uni_lost_and_found_app.ui.screens.item.ItemsLostScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ItemsFoundScreen()
        }
    }
}