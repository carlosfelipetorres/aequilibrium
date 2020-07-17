package com.ct.codetest.app

import androidx.multidex.MultiDexApplication
import com.ct.codetest.di.appComponent
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

open class MainApp : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        initiateKoin()
    }

    private fun initiateKoin() {
        startKoin{
            androidContext(this@MainApp)
            modules(provideDependency())
        }
    }

    open fun provideDependency() = appComponent
}