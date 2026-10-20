package ali.ghanbariyan.loginapp.mvp.model

import ali.ghanbariyan.loginapp.androidWrapper.DeviceInfo
import android.content.Context

class ModelMainActivity(private val context: Context) {

    fun getId() = DeviceInfo.getAndroidId(context)

}