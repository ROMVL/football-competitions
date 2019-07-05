package com.romanik.footballcompetitions.core

import android.app.Application
import com.romanik.footballcompetitions.di.appModule
import com.romanik.footballcompetitions.di.networkModule
import org.koin.android.ext.android.startKoin
import com.romanik.footballcompetitions.di.databaseModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, appModule + databaseModule + networkModule)
    }
}