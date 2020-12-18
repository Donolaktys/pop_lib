package ru.donolaktys.pop_lib.mvp.di.modules

import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.donolaktys.pop_lib.mvp.model.cache.IRepositoriesCache
import ru.donolaktys.pop_lib.mvp.model.cache.IUsersCache
import ru.donolaktys.pop_lib.mvp.model.cache.room.RoomGithubRepositoriesCache
import ru.donolaktys.pop_lib.mvp.model.cache.room.RoomGithubUsersCache
import ru.donolaktys.pop_lib.mvp.model.entity.room.Database
import ru.donolaktys.pop_lib.ui.App
import javax.inject.Singleton

@Module
class CacheModule {

    @Singleton
    @Provides
    fun database(app: App): Database = Room.databaseBuilder(app, Database::class.java, Database.DB_NAME).build()

    @Singleton
    @Provides
    fun usersCache(database: Database) : IUsersCache = RoomGithubUsersCache(database)

    @Singleton
    @Provides
    fun repositoriesCache(database: Database) : IRepositoriesCache = RoomGithubRepositoriesCache(database)
}