package ru.donolaktys.pop_lib.mvp.model.api

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Url
import ru.donolaktys.pop_lib.mvp.model.entity.GithubUser
import ru.donolaktys.pop_lib.mvp.model.entity.GithubRepo

interface IDataSource {

    @GET("users")
    fun getUsers(): Single<List<GithubUser>>

    @GET
    fun getUserRepos(@Url url: String): Single<List<GithubRepo>>
}