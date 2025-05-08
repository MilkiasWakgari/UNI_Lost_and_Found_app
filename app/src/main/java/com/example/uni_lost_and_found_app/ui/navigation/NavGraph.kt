// AppNavGraph.kt
package com.example.uni_lost_and_found_app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.uni_lost_and_found_app.found.features.auth.presentation.*
import com.example.uni_lost_and_found_app.features.items.presentation.*
import com.example.uni_lost_and_found_app.features.chat.presentation.ChatScreen
import com.example.uni_lost_and_found_app.features.profile.presentation.*
import androidx.navigation.NavHostController

@Composable
fun AppNavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(navController, startDestination = Screen.Welcome.route) {

        // Authentication
        composable(Screen.Welcome.route) {
            WelcomeScreen(navController)
        }
        composable(Screen.SignIn.route) {
            SignInScreen(navController)
        }
        composable(Screen.SignUp.route) {
            SignUpScreen(navController)
        }
        composable(Screen.ForgotPassword.route) {
            ForgotPasswordScreen(navController)
        }
        composable(Screen.OTPVerification.route) {
            OTPVerificationScreen(navController)
        }

        // Home + bottom tabs
        composable(Screen.Home.route) {
            HomeScreen(navController)
        }
        composable(Screen.FoundItems.route) {
            HomeFoundItemsTab(navController)
        }
        composable(Screen.LostItems.route) {
            HomeLostItemsTab(navController)
        }
        composable(Screen.ReportItem.route) {
            ReportItemScreen(navController)
        }
        composable(Screen.Messages.route) {
            MessagesScreen(navController)
        }
        composable(Screen.Profile.route) {
            ProfileScreen(navController)
        }

        // Item details & actions
        composable(
            Screen.ItemDetails.route,
            arguments = listOf(navArgument("itemId") { type = NavType.StringType })
        ) {
            val itemId = it.arguments?.getString("itemId")!!
            ItemDetailsScreen(navController, itemId)
        }
        composable(
            Screen.ClaimItem.route,
            arguments = listOf(navArgument("itemId") { type = NavType.StringType })
        ) {
            val itemId = it.arguments?.getString("itemId")!!
            ClaimItemScreen(navController, itemId)
        }
        composable(
            Screen.ContactOwner.route,
            arguments = listOf(navArgument("itemId") { type = NavType.StringType })
        ) {
            val itemId = it.arguments?.getString("itemId")!!
            ContactOwnerScreen(navController, itemId)
        }
        composable(
            Screen.AdminItemDetails.route,
            arguments = listOf(navArgument("itemId") { type = NavType.StringType })
        ) {
            val itemId = it.arguments?.getString("itemId")!!
            AdminItemDetailsScreen(navController, itemId)
        }

        // Chat
        composable(
            Screen.Chat.route,
            arguments = listOf(navArgument("chatId") { type = NavType.StringType })
        ) {
            val chatId = it.arguments?.getString("chatId")!!
            ChatScreen(navController, chatId)
        }

        // Profile & settings
        composable(Screen.EditProfile.route) {
            EditProfileScreen(navController)
        }
        composable(Screen.DeleteAccount.route) {
            DeleteAccountDialog(navController)
        }

        // My items
        composable(Screen.MyItems.route) {
            MyItemsScreen(navController)
        }
        composable(
            Screen.MyItemDetails.route,
            arguments = listOf(navArgument("itemId") { type = NavType.StringType })
        ) {
            val itemId = it.arguments?.getString("itemId")!!
            MyItemDetailsScreen(navController, itemId)
        }
    }
}
