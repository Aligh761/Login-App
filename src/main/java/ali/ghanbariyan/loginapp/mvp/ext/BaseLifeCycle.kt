package ali.ghanbariyan.loginapp.mvp.ext

interface BaseLifeCycle {

    fun onCreate()
    fun onDestroy() {}
    fun onStop() {}
}