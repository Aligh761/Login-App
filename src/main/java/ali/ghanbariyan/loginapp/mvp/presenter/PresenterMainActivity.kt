package ali.ghanbariyan.loginapp.mvp.presenter

import ali.ghanbariyan.loginapp.mvp.ext.BaseLifeCycle
import ali.ghanbariyan.loginapp.mvp.model.ModelMainActivity
import ali.ghanbariyan.loginapp.mvp.view.ViewMainActivity

class PresenterMainActivity(
    private val view: ViewMainActivity,
    private val model: ModelMainActivity
) : BaseLifeCycle {
    override fun onCreate() {
        view.onClickHandler(model.getId())
    }


}