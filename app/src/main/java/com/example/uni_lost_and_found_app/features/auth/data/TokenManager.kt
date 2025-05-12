package com.example.uni_lost_and_found_app.features.auth.data

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.JsonObject

class TokenManager(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun saveToken(token: String) {
        sharedPreferences.edit().putString("auth_token", token).apply()
    }

    fun getToken(): String? {
        return sharedPreferences.getString("auth_token", null)
    }

    fun clearToken() {
        sharedPreferences.edit().remove("auth_token").apply()
    }

    fun isTokenValid(): Boolean {
        val token = getToken() ?: return false
        try {
            // Decode the JWT token (it's in format: header.payload.signature)
            val parts = token.split(".")
            if (parts.size != 3) return false

            // Decode the payload (second part)
            val payload = String(android.util.Base64.decode(parts[1], android.util.Base64.DEFAULT))
            val jsonObject = gson.fromJson(payload, JsonObject::class.java)

            // Check if token is expired
            val exp = jsonObject.get("exp")?.asLong ?: return false
            val currentTime = System.currentTimeMillis() / 1000
            return exp > currentTime
        } catch (e: Exception) {
            return false
        }
    }
} 