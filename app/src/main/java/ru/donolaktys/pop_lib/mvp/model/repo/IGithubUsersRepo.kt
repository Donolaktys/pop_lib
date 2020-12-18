package ru.donolaktys.pop_lib.mvp.model.repo

import io.reactivex.rxjava3.core.Single
import ru.donolaktys.pop_lib.mvp.model.entity.GithubUser

interface IGithubUsersRepo {
    fun getUsers() : Single<List<GithubUser>>
}