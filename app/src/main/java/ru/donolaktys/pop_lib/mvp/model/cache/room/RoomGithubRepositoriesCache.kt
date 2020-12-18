package ru.donolaktys.pop_lib.mvp.model.cache.room

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.donolaktys.pop_lib.mvp.model.cache.IRepositoriesCache
import ru.donolaktys.pop_lib.mvp.model.entity.GithubRepo
import ru.donolaktys.pop_lib.mvp.model.entity.GithubUser
import ru.donolaktys.pop_lib.mvp.model.entity.room.RoomGithubRepository
import ru.donolaktys.pop_lib.mvp.model.entity.room.Database

class RoomGithubRepositoriesCache(val db: Database) : IRepositoriesCache {
    override fun putRepos(user: GithubUser, repositories: List<GithubRepo>) =
        Completable.fromAction {
            val roomUser = user.login?.let { db.userDao.findByLogin(it) } ?: throw java.lang.RuntimeException("No such users in database")
            val roomRepos = repositories.map {
                RoomGithubRepository(
                    it.id ?: "",
                    it.name ?: "",
                    roomUser.id
                )
            }
            db.repositoryDao.insert(roomRepos)
        }.subscribeOn(Schedulers.io())

    override fun getRepos(user: GithubUser) = Single.fromCallable {
        val roomUser = user.login?.let { db.userDao.findByLogin(it) } ?: throw java.lang.RuntimeException("No such users in database")
        db.repositoryDao.findForUser(roomUser.id).map {
            GithubRepo(it.id, it.name)
        }
    }.subscribeOn(Schedulers.io())
}