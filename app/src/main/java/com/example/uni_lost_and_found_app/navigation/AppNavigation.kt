package com.example.uni_lost_and_found_app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.uni_lost_and_found_app.features.auth.presentation.ui.screens.SignUpScreen
import com.example.uni_lost_and_found_app.features.auth.presentation.ui.screens.ForgotPasswordScreen
import com.example.uni_lost_and_found_app.features.auth.presentation.ui.screens.EnterCodeScreen
import com.example.uni_lost_and_found_app.features.auth.presentation.ui.screens.SignInScreen
import com.example.uni_lost_and_found_app.features.auth.presentation.ui.screens.WelcomeScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "welcome") {
        composable("welcome") {
            WelcomeScreen(
                onSignInClick = { navController.navigate("signin") },
                onSignUpClick = { navController.navigate("signup") }
            )
        }

        composable("signup") {
            SignUpScreen(
                onBack = { navController.popBackStack() },
                onSignUpSuccess = { navController.navigate("verify") }
            )
        }

        composable("signin") {
            SignInScreen(
                onBack = { navController.popBackStack() },
                onForgotPassword = { navController.navigate("forgot_password") },
                onSignInSuccess = { navController.navigate("home") }
            )
        }

        composable("forgot_password") {
            ForgotPasswordScreen(
                onBack = { navController.popBackStack() },
                onResetSuccess = { navController.navigate("verify") }
            )
        }

        composable("verify") {
            EnterCodeScreen(
                onConfirmClick = { navController.navigate("home") },
                onCancelClick = { navController.popBackStack() }
            )
        }
    }
}
