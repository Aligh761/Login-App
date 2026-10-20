package ali.ghanbariyan.loginapp.ui.activity

import ali.ghanbariyan.loginapp.mvp.ext.ActivityUtils
import ali.ghanbariyan.loginapp.mvp.model.ModelMainActivity
import ali.ghanbariyan.loginapp.mvp.presenter.PresenterMainActivity
import ali.ghanbariyan.loginapp.mvp.view.ViewMainActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope

class MainActivity : AppCompatActivity(), ActivityUtils {

    private lateinit var presenter: PresenterMainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val view = ViewMainActivity(this, this, lifecycleScope)
        setContentView(view.binding.root)

        presenter = PresenterMainActivity(view, ModelMainActivity(this))
        presenter.onCreate()
    }

    override fun finished() {
        finish()
    }
}
