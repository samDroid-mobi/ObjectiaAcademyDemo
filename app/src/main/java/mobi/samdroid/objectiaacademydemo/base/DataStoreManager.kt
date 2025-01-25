package mobi.samdroid.objectiaacademydemo.base

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

object DataStoreManager {
    private const val PREF_DATASTORE_APP_NAME = "objectia_academy_demo"
    const val PREF_KEY_REMEMBER_ME = "remember_me"
    const val PREF_KEY_USERNAME = "username"
    const val PREF_KEY_PASSWORD = "password"
    const val PREF_KEY_LOGGED_IN = "logged_in"

    val Context.dataStore by preferencesDataStore(name = PREF_DATASTORE_APP_NAME)

    suspend fun setRememberMe(context: Context, value: Boolean, key: Preferences.Key<Boolean>) {
        context.dataStore.edit { preferences ->
            preferences[key] = value
        }
    }

    suspend fun isRememberMeEnabled(context: Context, key: Preferences.Key<Boolean>): Boolean {
        val preferences = context.dataStore.data.first()
        return preferences[key] ?: false
    }

    suspend fun setUsername(context: Context, value: String, key: Preferences.Key<String>) {
        context.dataStore.edit { preferences ->
            preferences[key] = value
        }
    }

    suspend fun getUsername(context: Context, key: Preferences.Key<String>): String {
        val preferences = context.dataStore.data.first()
        return preferences[key] ?: ""
    }

    suspend fun setPassword(context: Context, value: String, key: Preferences.Key<String>) {
        context.dataStore.edit { preferences ->
            preferences[key] = value
        }
    }

    suspend fun getPassword(context: Context, key: Preferences.Key<String>): String {
        val preferences = context.dataStore.data.first()
        return preferences[key] ?: ""
    }

    suspend fun setLoggedIn(context: Context, value: Boolean, key: Preferences.Key<Boolean>) {
        context.dataStore.edit { preferences ->
            preferences[key] = value
        }
    }

    suspend fun isLoggedIn(context: Context, key: Preferences.Key<Boolean>): Boolean {
        val preferences = context.dataStore.data.first()
        return preferences[key] ?: false
    }
}