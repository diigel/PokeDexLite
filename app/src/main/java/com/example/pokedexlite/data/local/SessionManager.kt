package com.example.pokedexlite.data.local

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import androidx.core.content.edit

@Singleton
class SessionManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun saveSession(username: String) {
        prefs.edit { putString(KEY_USERNAME, username) }
    }

    fun getLoggedInUsername(): String? = prefs.getString(KEY_USERNAME, null)

    fun isLoggedIn(): Boolean = getLoggedInUsername() != null

    fun clearSession() {
        prefs.edit { remove(KEY_USERNAME) }
    }

    companion object {
        private const val PREFS_NAME = "pokedex_session"
        private const val KEY_USERNAME = "logged_in_user"
    }
}
