package ru.donolaktys.pop_lib.mvp.model

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}