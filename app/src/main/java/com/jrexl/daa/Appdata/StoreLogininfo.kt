package com.jrexl.daa.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

val Context.dataStore by preferencesDataStore("user_prefs")

object StoreLogininfo {

    private val KEY_MOBILE = stringPreferencesKey("mobile")
    private val KEY_LOGIN = stringPreferencesKey("login_state")

    // SAVE mobile & login state
    suspend fun saveLogin(context: Context, mobile: String) {
        context.dataStore.edit { pref ->
            pref[KEY_MOBILE] = mobile
            pref[KEY_LOGIN] = "true"
        }
    }

    // READ mobile (direct)
    suspend fun getMobile(context: Context): String? {
        val pref = context.dataStore.data.first()
        return pref[KEY_MOBILE]
    }

    // READ login state (direct)
    suspend fun isLoggedIn(context: Context): Boolean {
        val pref = context.dataStore.data.first()
        return pref[KEY_LOGIN] == "true"
    }

    // CLEAR
    suspend fun clear(context: Context) {
        context.dataStore.edit { it.clear() }
    }
}
