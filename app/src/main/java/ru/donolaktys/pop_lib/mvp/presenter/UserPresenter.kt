package ru.donolaktys.pop_lib.mvp.presenter

import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.donolaktys.pop_lib.mvp.model.entity.GithubRepo
import ru.donolaktys.pop_lib.mvp.model.entity.GithubUser
import ru.donolaktys.pop_lib.mvp.model.repo.IGithubRepositoryRepo
import ru.donolaktys.pop_lib.mvp.presenter.list.IReposListPresenter
import ru.donolaktys.pop_lib.mvp.view.IUserView
import ru.donolaktys.pop_lib.mvp.view.list.IRepoItemView
import ru.terrakok.cicerone.Router
import java.lang.RuntimeException
import javax.inject.Inject

class UserPresenter(val user: GithubUser) : MvpPresenter<IUserView>() {

    @Inject lateinit var router: Router
    @Inject lateinit var repositories: IGithubRepositoryRepo
    @Inject lateinit var uiScheduler: Scheduler

    class ReposListPresenter: IReposListPresenter {
        override var itemClickListener: ((IRepoItemView) -> Unit)? = null

        val repos = mutableListOf<GithubRepo>()

        override fun bindView(view: IRepoItemView) {
            val repo = repos[view.pos]
            repo.name?.let {
                view.setRepoName(it)
            }
        }

        override fun getCount(): Int = repos.size
    }

    val reposListPresenter = ReposListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        user.login?.let { viewState.setLogin(it) }
        viewState.init()
        loadData()
    }

    fun loadData() {
            repositories.getRepositories(user)
                ?.observeOn(uiScheduler)
                ?.subscribe({ repos ->
                    reposListPresenter.repos.clear()
                    reposListPresenter.repos.addAll(repos)
                    viewState.updateReposList()
                }, { e ->
                    throw RuntimeException("Error: ${e.message}")
                })
    }

    fun backClick(): Boolean {
        router.exit()
        return true
    }
}