package ru.donolaktys.pop_lib.mvp.presenter

import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.donolaktys.pop_lib.mvp.model.entity.GithubUser
import ru.donolaktys.pop_lib.mvp.model.repo.IGithubUsersRepo
import ru.donolaktys.pop_lib.mvp.presenter.list.IUsersListPresenter
import ru.donolaktys.pop_lib.mvp.view.IUsersView
import ru.donolaktys.pop_lib.mvp.view.list.IUserItemView
import ru.donolaktys.pop_lib.navigation.Screens
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class UsersPresenter() : MvpPresenter<IUsersView>() {

    @Inject lateinit var usersRepo: IGithubUsersRepo
    @Inject lateinit var router: Router
    @Inject lateinit var uiScheduler: Scheduler

    class UsersListPresenter: IUsersListPresenter {
        override var itemClickListener: ((IUserItemView) -> Unit)? = null

        val users = mutableListOf<GithubUser>()

        override fun bindView(view: IUserItemView) {
            val user = users[view.pos]
            user.login?.let { view.setLogin(it) }
            user.avatarUrl?.let { view.loadImage(it)}
        }

        override fun getCount(): Int = users.size

    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()

        loadData()

        usersListPresenter.itemClickListener = {view ->
            val user = usersListPresenter.users[view.pos]
            router.navigateTo(Screens.UserScreen(user))
        }
    }

    private fun loadData() {
        usersRepo.getUsers()
            .observeOn(uiScheduler)
            .subscribe({user ->
                usersListPresenter.users.clear()
                usersListPresenter.users.addAll(user)
                viewState.updateUsersList()
            }, { e ->
                e.printStackTrace()
        })
    }

    fun backClick(): Boolean {
        router.exit()
        return true
    }
}