package ru.donolaktys.pop_lib.mvp.di

import dagger.Component
import ru.donolaktys.pop_lib.mvp.di.modules.*
import ru.donolaktys.pop_lib.mvp.presenter.MainPresenter
import ru.donolaktys.pop_lib.mvp.presenter.UserPresenter
import ru.donolaktys.pop_lib.mvp.presenter.UsersPresenter
import ru.donolaktys.pop_lib.ui.activity.MainActivity
import ru.donolaktys.pop_lib.ui.adapter.UsersRvAdapter
import ru.donolaktys.pop_lib.ui.fragment.UserFragment
import ru.donolaktys.pop_lib.ui.fragment.UsersFragment
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        ApiModule::class,
        NavigationModule::class,
        RepoModule::class,
        CacheModule::class,
        ImageModule::class
    ]
)
interface AppComponent {
    fun inject(usersPresenter: UsersPresenter)
    fun inject(userPresenter: UserPresenter)
    fun inject(mainPresenter: MainPresenter)
}