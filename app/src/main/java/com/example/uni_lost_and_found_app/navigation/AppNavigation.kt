package com.example.uni_lost_and_found_app.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.uni_lost_and_found_app.features.admin.presentation.ui.screens.AdminDashboardScreen
import com.example.uni_lost_and_found_app.features.admin.presentation.viewmodel.AdminViewModel
import com.example.uni_lost_and_found_app.features.auth.data.api.AuthApi
import com.example.uni_lost_and_found_app.features.auth.presentation.ui.screens.EnterCodeScreen
import com.example.uni_lost_and_found_app.features.auth.presentation.ui.screens.ForgotPasswordScreen
import com.example.uni_lost_and_found_app.features.auth.presentation.ui.screens.SignInScreen
import com.example.uni_lost_and_found_app.features.auth.presentation.ui.screens.SignUpScreen
import com.example.uni_lost_and_found_app.features.auth.presentation.ui.screens.WelcomeScreen
import com.example.uni_lost_and_found_app.features.chat.domain.repository.ChatRepository
import com.example.uni_lost_and_found_app.features.chat.presentation.ui.ChatDetailScreen
import com.example.uni_lost_and_found_app.features.chat.presentation.ui.ChatListScreen
import com.example.uni_lost_and_found_app.features.item.data.repository.ItemRepository
import com.example.uni_lost_and_found_app.features.item.presentation.ui.ItemsLostScreen
import com.example.uni_lost_and_found_app.features.item.presentation.ui.ItemsScreen
import com.example.uni_lost_and_found_app.features.item.presentation.ui.ReportItemFoundScreen
import com.example.uni_lost_and_found_app.features.profile.presentation.ui.EditProfileScreen
import com.example.uni_lost_and_found_app.features.profile.presentation.ui.MyItemsScreen
import com.example.uni_lost_and_found_app.features.profile.presentation.ui.ProfileScreen
import com.example.uni_lost_and_found_app.features.profile.presentation.viewmodel.ProfileViewModel

// import com.example.uni_lost_and_found_app.features.chat.presentation.ChatListScreen
// import com.example.uni_lost_and_found_app.features.chat.presentation.ChatDetailScreen
// import com.example.uni_lost_and_found_app.features.chat.data.repository.ChatRepository

@Composable
fun AppNavigation(
    navController: NavHostController,
    itemRepository: ItemRepository,
    chatRepository: ChatRepository,
    adminViewModel: AdminViewModel,
    token: String,
    profileViewModel: ProfileViewModel,
    authApi: AuthApi
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
                onSignUpSuccess = { navController.navigate("signin") },
                authApi = authApi
            )
        }
        composable("signin") {
            SignInScreen(
                onBack = { navController.popBackStack() },
                onForgotPassword = { navController.navigate("forgot_password") },
                onSignInSuccess = { navController.navigate("items_found") },
                navController = navController,
                authApi = authApi
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
        composable("edit_profile") {
            EditProfileScreen(
                viewModel = profileViewModel,
                onBack = { navController.popBackStack() },
                onUpdate = { navController.popBackStack() }
            )
        }
        composable("my_items") {
            MyItemsScreenWrapper(
                profileViewModel = profileViewModel,
                onBack = { navController.popBackStack() }
            )
        }
        composable("profile") {
            val context = navController.context
            ProfileScreen(
                viewModel = profileViewModel,
                onProfileClick = { navController.navigate("edit_profile") },
                onItemsClick = { navController.navigate("my_items") },
                onLogout = {
                    clearToken(context)
                    navController.navigate("welcome") {
                        popUpTo("profile") { inclusive = true }
                    }
                },
                onDeleteAccount = {
                    clearToken(context)
                    navController.navigate("welcome") {
                        popUpTo("profile") { inclusive = true }
                    }
                },
                onBottomNavClick = { idx ->
                    when (idx) {
                        0 -> navController.navigate("items_found")
                        1 -> navController.navigate("items_lost")
                        2 -> navController.navigate("report_item_found")
                        3 -> navController.navigate("chats")
                        4 -> navController.navigate("profile")
                    }
                },
                selectedBottomNavIndex = 4
            )
        }
        // Add other composable routes as needed
        composable("admin_dashboard") {
            AdminDashboardScreen(
                viewModel = adminViewModel,
                token = token
            )
        }
        composable("chats") {
            ChatListScreen(
                chatRepository = chatRepository,
                onChatClick = { chatId ->
                    navController.navigate("chat_detail/$chatId")
                }
            )
        }
        composable("chat_detail/{chatId}") { backStackEntry ->
            val chatId = backStackEntry.arguments?.getString("chatId") ?: ""
            ChatDetailScreen(
                chatId = chatId,
                chatRepository = chatRepository,
                onNavigate = { route -> navController.navigate(route) }
            )
        }
    }
}

@Composable
fun MyItemsScreenWrapper(
    profileViewModel: ProfileViewModel,
    onBack: () -> Unit
) {
    val items = profileViewModel.myItems.collectAsState().value
    LaunchedEffect(Unit) { profileViewModel.loadMyItems() }
    MyItemsScreen(
        items = items,
        onBack = onBack,
        onItemClick = { /* TODO: Navigate to item details if needed */ }
    )
}

fun clearToken(context: Context) {
    val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    sharedPreferences.edit().remove("auth_token").apply()
}
