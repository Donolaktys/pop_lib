package ru.donolaktys.pop_lib.mvp.presenter

import moxy.MvpPresenter
import ru.donolaktys.pop_lib.mvp.view.IMainView
import ru.donolaktys.pop_lib.navigation.Screens
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MainPresenter() : MvpPresenter<IMainView>() {

    @Inject lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(Screens.UsersScreen())
    }

    fun backClick() {
        router.exit()
    }
}