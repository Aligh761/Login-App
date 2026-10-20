package ali.ghanbariyan.loginapp.mvp.view

import ali.ghanbariyan.loginapp.databinding.ActivityMainBinding
import ali.ghanbariyan.loginapp.mvp.ext.ActivityUtils
import ali.ghanbariyan.loginapp.remote.RetrofitServices
import ali.ghanbariyan.loginapp.remote.ext.ErrorUtils
import ali.ghanbariyan.loginapp.ui.activity.HomeActivity
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@SuppressLint("ViewConstructor")
class ViewMainActivity(
    private val context: Context,
    private val finish: ActivityUtils,
    // اسکوپ وابسته به چرخه‌ی حیات اکتیویتی (lifecycleScope) تا هنگام بسته شدن صفحه کنسل شود
    private val scope: CoroutineScope
) {
    val binding = ActivityMainBinding.inflate(LayoutInflater.from(context))

    // ایمیلی که کد برای آن ارسال شده است
    private var currentEmail: String = ""

    fun onClickHandler(androidId: String) {

        binding.btnSend.setOnClickListener {
            val email = binding.edtInputEmail.text.toString().trim()

            if (email.isEmpty()) {
                binding.textInputEmail.error = "ایمیل را وارد کنید"
                return@setOnClickListener
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.textInputEmail.error = "فرمت ایمیل صحیح نیست"
                return@setOnClickListener
            }
            binding.textInputEmail.error = null

            sendCodeToEmail(email)
        }

        binding.txtWrong.setOnClickListener {
            showEmailStep()
        }

        binding.btnConfirm.setOnClickListener {
            val code = binding.edtCode.text.toString().trim()

            if (code.length < 6) {
                binding.textInputCode.error = "کد باید ۶ رقم باشد"
                return@setOnClickListener
            }
            binding.textInputCode.error = null

            verifyCode(androidId, code, currentEmail)
        }
    }

    /** مرحله‌ی اول: وارد کردن ایمیل */
    private fun showEmailStep() {
        binding.btnSend.visibility = View.VISIBLE
        binding.textInputEmail.visibility = View.VISIBLE

        binding.txtSendEmail.visibility = View.GONE
        binding.textInputCode.visibility = View.GONE
        binding.btnConfirm.visibility = View.GONE
        binding.txtWrong.visibility = View.GONE

        binding.edtCode.text?.clear()
        binding.textInputCode.error = null
    }

    /** مرحله‌ی دوم: وارد کردن کد تایید */
    private fun showCodeStep(email: String) {
        binding.btnSend.visibility = View.GONE
        binding.textInputEmail.visibility = View.GONE

        binding.txtSendEmail.visibility = View.VISIBLE
        binding.textInputCode.visibility = View.VISIBLE
        binding.btnConfirm.visibility = View.VISIBLE
        binding.txtWrong.visibility = View.VISIBLE

        binding.txtSendEmail.text = "کد به ایمیل $email ارسال شد"
    }

    private fun sendCodeToEmail(email: String) {
        binding.btnSend.isEnabled = false

        // اسکوپ lifecycleScope به‌صورت پیش‌فرض روی Main اجرا می‌شود و
        // توابع suspend رتروفیت خودشان روی ترد پس‌زمینه اجرا می‌شوند
        scope.launch {
            try {
                val response = RetrofitServices.apiServices.sendRequest(email)

                if (response.isSuccessful) {
                    // فقط در صورت موفقیت به مرحله‌ی وارد کردن کد می‌رویم
                    currentEmail = email
                    showCodeStep(email)
                    response.body()?.message?.let { showToast(it) }
                } else {
                    showToast(ErrorUtils.getError(response))
                }
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                Log.e("ServerError", "sendCodeToEmail failed", e)
                showToast("مشکل در ارتباط با سرور")
            } finally {
                binding.btnSend.isEnabled = true
            }
        }
    }

    private fun verifyCode(androidId: String, code: String, email: String) {
        binding.btnConfirm.isEnabled = false

        scope.launch {
            try {
                val response = RetrofitServices.apiServices.verifyEmail(androidId, code, email)

                if (response.isSuccessful) {
                    showToast("لاگین شما موفقیت آمیز بود", Toast.LENGTH_LONG)

                    // اجرا روی ترد اصلی (قبلاً روی ترد IO صدا زده می‌شد)
                    context.startActivity(Intent(context, HomeActivity::class.java))
                    finish.finished()
                } else {
                    showToast(ErrorUtils.getError(response))
                }
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                Log.e("ServerError", "verifyCode failed", e)
                showToast("مشکل در ارتباط با سرور")
            } finally {
                binding.btnConfirm.isEnabled = true
            }
        }
    }

    private fun showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(context, message, duration).show()
    }
}
