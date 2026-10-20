package ali.ghanbariyan.loginapp.remote.ext

import ali.ghanbariyan.loginapp.remote.dataModel.ErrorModel
import com.google.gson.Gson
import retrofit2.Response

object ErrorUtils {

    private const val DEFAULT_ERROR = "مشکل در ارتباط با سرور"

    fun getError(response: Response<*>): String {
        return try {
            // errorBody.toString() فقط آدرس شیء را برمی‌گرداند؛ باید string() صدا زده شود
            val body = response.errorBody()?.string()
            if (body.isNullOrBlank()) {
                DEFAULT_ERROR
            } else {
                Gson().fromJson(body, ErrorModel::class.java)?.message ?: DEFAULT_ERROR
            }
        } catch (e: Exception) {
            DEFAULT_ERROR
        }
    }
}
