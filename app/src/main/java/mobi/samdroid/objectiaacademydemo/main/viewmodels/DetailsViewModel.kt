package mobi.samdroid.objectiaacademydemo.main.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.JsonObject
import mobi.samdroid.objectiaacademydemo.base.models.ObjectiaUser
import mobi.samdroid.objectiaacademydemo.base.retrofit.APIClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsViewModel: ViewModel() {
    lateinit var user: ObjectiaUser

    private var mLiveDescription = MutableLiveData<String>()

    fun liveDescription() = mLiveDescription

    fun getDescription() {
        val call = APIClient.apiService.getDescription("2")

        call.enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    mLiveDescription.value = body?.get("text")?.asString
                } else {
                    // Handle error
                }
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                t
            }
        })
    }
}