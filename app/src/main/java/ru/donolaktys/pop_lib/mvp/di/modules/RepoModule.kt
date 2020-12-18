package ru.donolaktys.pop_lib.mvp.di.modules

import dagger.Module
import dagger.Provides
import ru.donolaktys.pop_lib.mvp.model.api.IDataSource
import ru.donolaktys.pop_lib.mvp.model.cache.IRepositoriesCache
import ru.donolaktys.pop_lib.mvp.model.cache.IUsersCache
import ru.donolaktys.pop_lib.mvp.model.network.INetworkStatus
import ru.donolaktys.pop_lib.mvp.model.repo.IGithubRepositoryRepo
import ru.donolaktys.pop_lib.mvp.model.repo.IGithubUsersRepo
import ru.donolaktys.pop_lib.mvp.model.repo.RetrofitGithubRepositoriesRepo
import ru.donolaktys.pop_lib.mvp.model.repo.RetrofitGithubUsersRepo
import javax.inject.Singleton

@Module
class RepoModule {

    @Singleton
    @Provides
    fun usersRepo(api: IDataSource, networkStatus: INetworkStatus, cache: IUsersCache) : IGithubUsersRepo =
        RetrofitGithubUsersRepo(api, networkStatus, cache)

    @Singleton
    @Provides
    fun repositoriesRepo(api: IDataSource, networkStatus: INetworkStatus, cache: IRepositoriesCache) : IGithubRepositoryRepo =
        RetrofitGithubRepositoriesRepo(api, networkStatus, cache)
}