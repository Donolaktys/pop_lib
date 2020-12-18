package ru.donolaktys.pop_lib.mvp.model.image

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}