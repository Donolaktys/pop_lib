package ru.donolaktys.pop_lib.mvp.view.list

interface IUserItemView : IItemView {
    fun setLogin(text: String)
    fun loadImage(url: String)
}