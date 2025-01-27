package mobi.samdroid.objectiaacademydemo.base.retrofit

object APIClient {
    val apiService: APIService by lazy {
        RetrofitClient.retrofit.create(APIService::class.java)
    }
}