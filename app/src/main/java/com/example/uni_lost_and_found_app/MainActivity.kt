package com.example.uni_lost_and_found_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.uni_lost_and_found_app.features.admin.application.usecase.GetAllUsersUseCase
import com.example.uni_lost_and_found_app.features.admin.presentation.viewmodel.AdminViewModel
import com.example.uni_lost_and_found_app.navigation.AppNavigation
import com.example.uni_lost_and_found_app.ui.theme.UNILostAndFoundAppTheme
import com.example.uni_lost_and_found_app.features.item.data.repository.ItemRepository
import com.example.uni_lost_and_found_app.features.item.data.api.ItemApiService
import com.example.uni_lost_and_found_app.features.chat.data.api.ChatApiService
import com.example.uni_lost_and_found_app.features.chat.data.repository.ChatRepositoryImpl
import com.example.uni_lost_and_found_app.features.chat.domain.repository.ChatRepository
import com.example.uni_lost_and_found_app.features.admin.application.usecase.DeleteUserUseCase
import com.example.uni_lost_and_found_app.features.admin.application.usecase.PromoteUserUseCase
import com.example.uni_lost_and_found_app.features.admin.application.usecase.DemoteUserUseCase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.uni_lost_and_found_app.features.admin.data.api.AdminApi
import com.example.uni_lost_and_found_app.features.admin.data.repository.AdminRepositoryImpl
import com.example.uni_lost_and_found_app.features.profile.presentation.viewmodel.ProfileViewModel
import com.example.uni_lost_and_found_app.features.profile.data.repository.ProfileRepositoryImpl
import com.example.uni_lost_and_found_app.features.profile.data.api.UserApiService
import android.content.Context

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create Retrofit instance
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:5000/api/") // Use 10.0.2.2 for Android emulator to access localhost
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Create API services
        val itemApiService = retrofit.create(ItemApiService::class.java)
        val chatApiService = retrofit.create(ChatApiService::class.java)
        val adminApiService = retrofit.create(AdminApi::class.java)
        val userApiService = retrofit.create(UserApiService::class.java)

        // Create repositories
        val itemRepository = ItemRepository(itemApiService)
        val chatRepository: ChatRepository = ChatRepositoryImpl(chatApiService)
        val adminRepository = AdminRepositoryImpl(adminApiService)
        val profileRepository = ProfileRepositoryImpl(userApiService)
        val profileViewModel = ProfileViewModel(profileRepository)

        // Create admin use cases
        val getAllUsersUseCase = GetAllUsersUseCase(adminRepository)
        val deleteUserUseCase = DeleteUserUseCase(adminRepository)
        val promoteUserUseCase = PromoteUserUseCase(adminRepository)
        val demoteUserUseCase = DemoteUserUseCase(adminRepository)

        // Create AdminViewModel with use cases
        val adminViewModel = AdminViewModel(
            getAllUsersUseCase = getAllUsersUseCase,
            deleteUserUseCase = deleteUserUseCase,
            promoteUserUseCase = promoteUserUseCase,
            demoteUserUseCase = demoteUserUseCase
        )

        // Retrieve token from SharedPreferences
        val sharedPreferences = getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("auth_token", "") ?: ""

        setContent {
            UNILostAndFoundAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    AppNavigation(
                        navController = navController,
                        itemRepository = itemRepository,
                        chatRepository = chatRepository,
                        adminViewModel = adminViewModel,
                        token = token,
                        profileViewModel = profileViewModel
                    )
                }
            }
        }
    }
}
