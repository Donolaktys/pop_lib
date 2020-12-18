package ru.donolaktys.pop_lib.ui.activity

import android.os.Bundle
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.donolaktys.pop_lib.R
import ru.donolaktys.pop_lib.mvp.presenter.MainPresenter
import ru.donolaktys.pop_lib.mvp.view.IMainView
import ru.donolaktys.pop_lib.ui.App
import ru.donolaktys.pop_lib.ui.BackButtonListener
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), IMainView  {

    @Inject lateinit var navigatorHolder : NavigatorHolder

    val navigator = SupportAppNavigator(this, supportFragmentManager, R.id.container)

    private val presenter: MainPresenter by moxyPresenter {
        MainPresenter().apply { App.component.inject( this )}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        App.component.inject(this)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if(it is BackButtonListener && it.backPressed()){
                return
            }
        }
        presenter.backClick()
    }
}