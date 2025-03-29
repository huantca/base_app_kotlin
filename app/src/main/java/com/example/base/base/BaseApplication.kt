package com.example.base.base

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        BasePreferences.initPrefs(applicationContext)
        //fetchRemoteConfig()
        //setupTimber()
    }

}
