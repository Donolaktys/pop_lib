package ru.donolaktys.pop_lib.mvp.presenter.list

import ru.donolaktys.pop_lib.mvp.view.list.IItemView

interface IListPresenter<V: IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}