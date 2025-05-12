package com.example.uni_lost_and_found_app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.uni_lost_and_found_app.features.admin.presentation.ui.screens.AdminDashboardScreen
import com.example.uni_lost_and_found_app.features.admin.presentation.viewmodel.AdminViewModel
import com.example.uni_lost_and_found_app.features.auth.presentation.ui.screens.ForgotPasswordScreen
import com.example.uni_lost_and_found_app.features.auth.presentation.ui.screens.SignInScreen
import com.example.uni_lost_and_found_app.features.auth.presentation.ui.screens.SignUpScreen
import com.example.uni_lost_and_found_app.features.auth.presentation.ui.screens.WelcomeScreen
import com.example.uni_lost_and_found_app.features.auth.presentation.ui.screens.EnterCodeScreen
import com.example.uni_lost_and_found_app.features.item.presentation.ui.ItemsScreen
import com.example.uni_lost_and_found_app.features.item.presentation.ui.ItemsLostScreen
import com.example.uni_lost_and_found_app.features.item.presentation.ui.ReportItemFoundScreen
import com.example.uni_lost_and_found_app.features.item.data.repository.ItemRepository
import com.example.uni_lost_and_found_app.features.chat.data.repository.ChatRepository
// import com.example.uni_lost_and_found_app.features.chat.presentation.ChatListScreen
// import com.example.uni_lost_and_found_app.features.chat.presentation.ChatDetailScreen
// import com.example.uni_lost_and_found_app.features.chat.data.repository.ChatRepository

@Composable
fun AppNavigation(
    navController: NavHostController,
    itemRepository: ItemRepository,
    chatRepository: ChatRepository,
    adminViewModel: AdminViewModel,
    token: String
) {
    NavHost(
        navController = navController,
        startDestination = "welcome"
    ) {
        composable("welcome") {
            WelcomeScreen(
                onSignInClick = { navController.navigate("signin") },
                onSignUpClick = { navController.navigate("signup") }
            )
        }
        composable("signup") {
            SignUpScreen(
                onBack = { navController.popBackStack() },
                onSignUpSuccess = { navController.navigate("signin") }
            )
        }
        composable("signin") {
            SignInScreen(
                onBack = { navController.popBackStack() },
                onForgotPassword = { navController.navigate("forgot_password") },
                onSignInSuccess = { navController.navigate("items_found") },
                navController = navController
            )
        }
        composable("forgot_password") {
            ForgotPasswordScreen(
                onBack = { navController.popBackStack() },
                onResetSuccess = { navController.navigate("enter_code") }
            )
        }
        composable("enter_code") {
            EnterCodeScreen(
                onConfirmClick = { navController.navigate("signin") },
                onCancelClick = { navController.popBackStack() }
            )
        }
        composable("items_found") {
            ItemsScreen(
                title = "Items Found",
                currentRoute = "items_found",
                itemRepository = itemRepository,
                onNavigate = { route -> navController.navigate(route) }
            )
        }
        composable("items_lost") {
            ItemsLostScreen(
                itemRepository = itemRepository,
                onNavigate = { route -> navController.navigate(route) }
            )
        }
        composable("report_item_found") {
            ReportItemFoundScreen(
                onNavigate = { route: String -> navController.navigate(route) },
                itemRepository = itemRepository
            )
        }
        // Add other composable routes as needed
        composable("admin_dashboard") {
            AdminDashboardScreen(
                viewModel = adminViewModel,
                token = token
            )
        }
    }
}
