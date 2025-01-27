package mobi.samdroid.objectiaacademydemo.base.retrofit

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("loremipsum")
    fun getDescription(@Query("paragraphs") nb: String): Call<JsonObject>
}