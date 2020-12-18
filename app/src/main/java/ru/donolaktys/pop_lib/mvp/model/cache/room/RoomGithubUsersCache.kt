package ru.donolaktys.pop_lib.mvp.model.cache.room

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.donolaktys.pop_lib.mvp.model.cache.IUsersCache
import ru.donolaktys.pop_lib.mvp.model.entity.GithubUser
import ru.donolaktys.pop_lib.mvp.model.entity.room.RoomGithubUser
import ru.donolaktys.pop_lib.mvp.model.entity.room.Database

class RoomGithubUsersCache(val db: Database) : IUsersCache {

    override fun putUsers(users: List<GithubUser>) = Completable.fromAction {
        val roomUsers = users.map { user ->
            RoomGithubUser(
                user.id ?: "",
                user.login ?: "",
                user.avatarUrl ?: "",
                user.reposUrl ?: ""
            )
        }
        db.userDao.insert(roomUsers)
    }.subscribeOn(Schedulers.io())

    override fun getUsers()= Single.fromCallable {
        db.userDao.getAll().map { roomUser ->
            GithubUser(roomUser.id, roomUser.login, roomUser.avatarUrl, roomUser.reposUrl)
        }
    }.subscribeOn(Schedulers.io())

}