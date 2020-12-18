package ru.donolaktys.pop_lib.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface IUserView: MvpView {
    fun setLogin(login: String)
    fun init()
    fun updateReposList()
}