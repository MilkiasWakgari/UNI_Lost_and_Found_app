package com.example.uni_lost_and_found_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.uni_lost_and_found_app.navigation.AppNavigation
import com.example.uni_lost_and_found_app.ui.theme.UNILostAndFoundAppTheme
import com.example.uni_lost_and_found_app.features.item.data.repository.ItemRepository
import com.example.uni_lost_and_found_app.features.item.data.api.ItemApiService
import com.example.uni_lost_and_found_app.features.chat.data.api.ChatApiService
import com.example.uni_lost_and_found_app.features.chat.data.repository.ChatRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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

        // Create repositories
        val itemRepository = ItemRepository(itemApiService)
        val chatRepository = ChatRepository(chatApiService)

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
                        chatRepository = chatRepository
                    )
                }
            }
        }
    }
}
