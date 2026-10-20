package ali.ghanbariyan.loginapp.remote.apiServices

import ali.ghanbariyan.loginapp.remote.dataModel.DefaultModel
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST


interface LoginApiServices {
    @FormUrlEncoded
    @POST("email/login")
    suspend fun sendRequest(
        @Field("email") email: String
    ): Response<DefaultModel>


    @FormUrlEncoded
    @POST("email/login/verify")
    suspend fun verifyEmail(
        @Header("app-device-uid") androidId: String,
        @Field("code") code: String,
        @Field("email") email: String
    ): Response<DefaultModel>

}