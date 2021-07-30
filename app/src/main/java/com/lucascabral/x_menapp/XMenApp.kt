package com.lucascabral.x_menapp

import android.app.Application
import com.lucascabral.x_menapp.ui.di.xMenModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class XMenApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@XMenApp)
            modules(
                listOf(xMenModule)
            )
        }
    }
}