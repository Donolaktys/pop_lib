package ru.donolaktys.pop_lib.mvp.model.repo

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.donolaktys.pop_lib.mvp.model.api.IDataSource
import ru.donolaktys.pop_lib.mvp.model.cache.IRepositoriesCache
import ru.donolaktys.pop_lib.mvp.model.entity.GithubRepo
import ru.donolaktys.pop_lib.mvp.model.entity.GithubUser
import ru.donolaktys.pop_lib.mvp.model.network.INetworkStatus

class RetrofitGithubRepositoriesRepo(val api: IDataSource, val networkStatus: INetworkStatus, val cache: IRepositoriesCache
) : IGithubRepositoryRepo {
    override fun getRepositories(user: GithubUser) =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                user.reposUrl?.let { url ->
                    api.getUserRepos(url).flatMap { repositories ->
                        cache.putRepos(user, repositories).andThen(Single.just(repositories))
                    }
                } ?: Single.error<List<GithubRepo>>(RuntimeException("User has no repos url"))
                    .subscribeOn(Schedulers.io())
            } else {
                cache.getRepos(user)
            }
        }.subscribeOn(Schedulers.io())
}
