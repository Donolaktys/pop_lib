package ru.donolaktys.pop_lib.ui

import android.app.Application
import ru.donolaktys.pop_lib.mvp.di.AppComponent
import ru.donolaktys.pop_lib.mvp.di.DaggerAppComponent
import ru.donolaktys.pop_lib.mvp.di.modules.AppModule

class App: Application() {

    companion object {
        lateinit var instance: App
        val component get() = instance._appComponent
    }

    private lateinit var _appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        _appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}