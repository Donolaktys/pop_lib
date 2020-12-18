package ru.donolaktys.pop_lib.mvp.model.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.donolaktys.pop_lib.mvp.model.entity.GithubRepo
import ru.donolaktys.pop_lib.mvp.model.entity.GithubUser

interface IRepositoriesCache {
    fun putRepos(user: GithubUser, repositories: List<GithubRepo>) : Completable
    fun getRepos(user: GithubUser) : Single<List<GithubRepo>>
}