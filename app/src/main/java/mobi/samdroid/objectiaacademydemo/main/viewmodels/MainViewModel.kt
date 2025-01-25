package mobi.samdroid.objectiaacademydemo.main.viewmodels

import androidx.lifecycle.ViewModel
import mobi.samdroid.objectiaacademydemo.base.models.ObjectiaUser

class MainViewModel: ViewModel() {
    lateinit var user: ObjectiaUser

    fun getUsers(): ArrayList<ObjectiaUser> {
        return arrayListOf(
            ObjectiaUser("Sam", "Shouman", "+961 3 943 517"),
            ObjectiaUser("Imad", "Hassan", "+961 3 123 456"),
            ObjectiaUser("Loai", "Darsa", "+961 3 777 456"),
            ObjectiaUser("Rayane", "Khaled", "+961 3 452 001"),
        )
    }
}