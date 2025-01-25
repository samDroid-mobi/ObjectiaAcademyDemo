package mobi.samdroid.objectiaacademydemo.main.viewmodels

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mobi.samdroid.objectiaacademydemo.base.DataStoreManager

class MainParentViewModel: ViewModel() {

    fun logout(context: Context) {
        viewModelScope.launch {
            DataStoreManager.setLoggedIn(
                context,
                false,
                booleanPreferencesKey(DataStoreManager.PREF_KEY_LOGGED_IN)
            )
        }
    }
}