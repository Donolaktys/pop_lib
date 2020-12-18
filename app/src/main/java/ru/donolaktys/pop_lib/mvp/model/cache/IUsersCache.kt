package ru.donolaktys.pop_lib.mvp.model.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.donolaktys.pop_lib.mvp.model.entity.GithubUser

interface IUsersCache {
    fun putUsers(users: List<GithubUser>): Completable
    fun getUsers(): Single<List<GithubUser>>
}