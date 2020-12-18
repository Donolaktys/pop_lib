package ru.donolaktys.pop_lib.mvp.model.repo

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.donolaktys.pop_lib.mvp.model.api.IDataSource
import ru.donolaktys.pop_lib.mvp.model.cache.IUsersCache
import ru.donolaktys.pop_lib.mvp.model.network.INetworkStatus

class RetrofitGithubUsersRepo(val api: IDataSource, val networkStatus: INetworkStatus, val cache: IUsersCache): IGithubUsersRepo  {
    override fun getUsers() = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            api.getUsers().flatMap { users -> cache.putUsers(users).andThen(Single.just(users))}
        } else {
           cache.getUsers()
        }
    }.subscribeOn(Schedulers.io())

}