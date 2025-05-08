// Screen.kt
package com.example.uni_lost_and_found_app.ui.navigation

sealed class Screen(val route: String) {
    // Authentication flow
    object Welcome         : Screen("welcome")
    object SignIn          : Screen("sign_in")
    object SignUp          : Screen("sign_up")
    object ForgotPassword  : Screen("forgot_password")
    object OTPVerification : Screen("otp_verification")

    // Main app
    object Home            : Screen("home")

    // Item tabs inside Home
    object FoundItems      : Screen("found_items")
    object LostItems       : Screen("lost_items")
    object ReportItem      : Screen("report_item")

    // Chat & messages
    object Chat            : Screen("chat/{chatId}") {
        fun pass(chatId: String) = "chat/$chatId"
    }
    object Messages        : Screen("messages")

    // Item details flows
    object ItemDetails     : Screen("item_details/{itemId}") {
        fun pass(itemId: String) = "item_details/$itemId"
    }
    object ClaimItem       : Screen("claim_item/{itemId}") {
        fun pass(itemId: String) = "claim_item/$itemId"
    }
    object ContactOwner    : Screen("contact_owner/{itemId}") {
        fun pass(itemId: String) = "contact_owner/$itemId"
    }
    object AdminItemDetails: Screen("admin_item_details/{itemId}") {
        fun pass(itemId: String) = "admin_item_details/$itemId"
    }

    // Profile & settings
    object Profile         : Screen("profile")
    object EditProfile     : Screen("edit_profile")
    object DeleteAccount   : Screen("delete_account")

    // My items
    object MyItems         : Screen("my_items")
    object MyItemDetails   : Screen("my_item_details/{itemId}") {
        fun pass(itemId: String) = "my_item_details/$itemId"
    }
}
