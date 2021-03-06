package ru.donolaktys.pop_lib.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface IUsersView: MvpView {
    fun init()
    fun updateUsersList()
}