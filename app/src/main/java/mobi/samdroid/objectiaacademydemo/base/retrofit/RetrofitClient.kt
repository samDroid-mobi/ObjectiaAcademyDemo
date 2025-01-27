package mobi.samdroid.objectiaacademydemo.base.retrofit

import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://api.api-ninjas.com/v1/"

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient().build())
            .build()
    }

    private fun okHttpClient() = OkHttpClient().newBuilder()
        .addInterceptor { chain ->
            val request: Request = chain.request()
                .newBuilder()
                .header("X-Api-Key", "7QZkbPEzbdLH/+om2IR5Bg==Oj6sa5FbIwAyzmEo")
                .build()
            chain.proceed(request)
        }
}