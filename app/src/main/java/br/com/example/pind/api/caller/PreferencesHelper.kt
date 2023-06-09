package br.com.example.pind.api.caller

import android.content.Context
import androidx.core.content.edit

class PreferencesHelper(context: Context) {
    companion object{
        private const val SHARED_PREFERENCES_NAME = "PindPrefFile"
        private const val TOKEN = "token"
        private const val REFRESH_TOKEN = "refresh_token"

    }
    private val sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)

    fun getToken(): String? = sharedPreferences.getString(TOKEN, null)

    fun setToken(token: String?) {
        sharedPreferences.edit {
            putString(TOKEN, token)
        }
    }

    fun getRefreshToken(): String? = sharedPreferences.getString(REFRESH_TOKEN, null)

    fun setRefreshToken(token: String?) {
        sharedPreferences.edit {
            putString(REFRESH_TOKEN, token)
        }
    }


}