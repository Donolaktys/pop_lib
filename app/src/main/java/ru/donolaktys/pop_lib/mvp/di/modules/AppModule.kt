package ru.donolaktys.pop_lib.mvp.di.modules

import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import ru.donolaktys.pop_lib.ui.App

@Module
class AppModule(val app: App) {

    @Provides
    fun uiScheduler(): Scheduler = AndroidSchedulers.mainThread()

    @Provides
    fun app() : App {
        return app
    }
}