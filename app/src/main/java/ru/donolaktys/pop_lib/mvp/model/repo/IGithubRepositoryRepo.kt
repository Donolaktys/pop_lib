package ru.donolaktys.pop_lib.mvp.model.repo

import io.reactivex.rxjava3.core.Single
import ru.donolaktys.pop_lib.mvp.model.entity.GithubRepo
import ru.donolaktys.pop_lib.mvp.model.entity.GithubUser

interface IGithubRepositoryRepo {
    fun getRepositories(user: GithubUser) : Single<List<GithubRepo>>?
}