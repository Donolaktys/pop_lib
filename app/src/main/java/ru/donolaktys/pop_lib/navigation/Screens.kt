package ru.donolaktys.pop_lib.navigation

import ru.donolaktys.pop_lib.mvp.model.entity.GithubUser
import ru.donolaktys.pop_lib.ui.fragment.UserFragment
import ru.donolaktys.pop_lib.ui.fragment.UsersFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    class UsersScreen: SupportAppScreen() {
        override fun getFragment() = UsersFragment.newInstance()
    }

    class UserScreen(val user: GithubUser): SupportAppScreen() {
        override fun getFragment() = UserFragment.newInstance(user)
    }
}