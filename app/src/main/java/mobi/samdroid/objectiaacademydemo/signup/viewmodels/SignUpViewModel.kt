package mobi.samdroid.objectiaacademydemo.signup.viewmodels

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mobi.samdroid.objectiaacademydemo.base.DataStoreManager

class SignUpViewModel : ViewModel() {
    private val mLiveUsername = MutableLiveData<String>()
    private val mLivePassword = MutableLiveData<String>()
    private val mLiveRememberMe = MutableLiveData<Boolean>()
    private val mLiveLoggedIn = MutableLiveData<Boolean>()

    fun liveUsername(): LiveData<String> {
        return mLiveUsername
    }

    fun livePassword(): LiveData<String> {
        return mLivePassword
    }

    fun liveRememberMe(): LiveData<Boolean> {
        return mLiveRememberMe
    }

    fun liveLoggedIn(): LiveData<Boolean> {
        return mLiveLoggedIn
    }

    fun isInputValidated(username: String, password: String): Boolean {
        return username.isNotEmpty() && password.isNotEmpty()
    }

    fun isUsernameLengthValid(username: String): Boolean {
        return username.length >= 3
    }

    fun saveData(context: Context, isRememberMe: Boolean, username: String, password: String) {
        viewModelScope.launch {
            DataStoreManager.setRememberMe(
                context,
                isRememberMe,
                booleanPreferencesKey(DataStoreManager.PREF_KEY_REMEMBER_ME)
            )

            DataStoreManager.setLoggedIn(
                context,
                true,
                booleanPreferencesKey(DataStoreManager.PREF_KEY_LOGGED_IN)
            )

            if (isRememberMe) {
                DataStoreManager.setUsername(
                    context,
                    username,
                    stringPreferencesKey(DataStoreManager.PREF_KEY_USERNAME)
                )
                DataStoreManager.setPassword(
                    context,
                    password,
                    stringPreferencesKey(DataStoreManager.PREF_KEY_PASSWORD)
                )
            } else {
                DataStoreManager.setUsername(
                    context,
                    "",
                    stringPreferencesKey(DataStoreManager.PREF_KEY_USERNAME)
                )
                DataStoreManager.setPassword(
                    context,
                    "",
                    stringPreferencesKey(DataStoreManager.PREF_KEY_PASSWORD)
                )
            }
        }
    }

    fun getSavedData(context: Context) {
        viewModelScope.launch {
            val isRememberMe = DataStoreManager.isRememberMeEnabled(
                context,
                booleanPreferencesKey(DataStoreManager.PREF_KEY_REMEMBER_ME)
            )

            val username = DataStoreManager.getUsername(
                context,
                stringPreferencesKey(DataStoreManager.PREF_KEY_USERNAME)
            )

            val password = DataStoreManager.getPassword(
                context,
                stringPreferencesKey(DataStoreManager.PREF_KEY_PASSWORD)
            )

            val isLoggedIn = DataStoreManager.isLoggedIn(
                context,
                booleanPreferencesKey(DataStoreManager.PREF_KEY_LOGGED_IN)
            )

            mLiveRememberMe.value = isRememberMe
            mLiveUsername.value = username
            mLivePassword.value = password
            mLiveLoggedIn.value = isLoggedIn
        }
    }
}