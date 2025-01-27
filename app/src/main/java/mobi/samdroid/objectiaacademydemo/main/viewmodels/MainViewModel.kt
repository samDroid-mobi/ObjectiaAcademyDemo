package mobi.samdroid.objectiaacademydemo.main.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mobi.samdroid.objectiaacademydemo.base.database.AppDatabase
import mobi.samdroid.objectiaacademydemo.base.models.ObjectiaUser

class MainViewModel: ViewModel() {
    lateinit var user: ObjectiaUser
    private val mLiveGetUsers = MutableLiveData<ArrayList<ObjectiaUser>>()

    fun liveGetUsers(): MutableLiveData<ArrayList<ObjectiaUser>> {
        return mLiveGetUsers
    }

    fun getUsers(context: Context) {
        val db = AppDatabase.getDatabase(context)

        viewModelScope.launch {
            val userDao = db.objectiaUserDao()
            mLiveGetUsers.value = userDao.getAllUsers() as ArrayList<ObjectiaUser>
        }
    }
}