package com.backbase.assignment.ui

import android.app.Application
import ccom.backbase.assignment.ui.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


/**
 * Application context
 */
class MyApplication:Application(){
    override fun onCreate() {
        super.onCreate()
        // start Koin context
        startKoin {
            androidContext(this@MyApplication)
            androidLogger()
            modules(appModule)
        }
    }
}