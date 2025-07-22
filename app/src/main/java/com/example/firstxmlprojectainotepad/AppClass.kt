package com.example.firstxmlprojectainotepad

import android.app.Application
import com.example.firstxmlprojectainotepad.presentation.features.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class AppClass : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppClass)
            modules(appModule)
        }
    }
}
