package ali.ghanbariyan.loginapp.remote

import ali.ghanbariyan.loginapp.remote.apiServices.LoginApiServices
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitServices {
    private const val BASEURL = "https://learn.alirezaahmadi.info/api/v1/auth/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASEURL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiServices: LoginApiServices = retrofit.create(LoginApiServices::class.java)
}