package com.github.syafiqq.androidmvptest001.controller

import android.util.Log
import com.github.syafiqq.androidmvptest001.BuildConfig
import com.github.syafiqq.androidmvptest001.model.di.component.AppComponent
import com.github.syafiqq.androidmvptest001.model.di.component.DaggerAppComponent
import com.github.syafiqq.ext.dagger.android.DaggerApplication
import dagger.android.AndroidInjector
import timber.log.Timber

class App : DaggerApplication() {
    override fun applicationInjector(
        cls: Class<*>?,
        holders: MutableMap<Class<*>, Any>?
    ): AndroidInjector<*> {
        return DaggerAppComponent.factory().create(this)
    }

    override fun rootComponent(): Class<*> = AppComponent::class.java

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(CrashReportingTree())
        }
    }

    private class CrashReportingTree : Timber.Tree() {
        override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
            if (priority == Log.VERBOSE || priority == Log.DEBUG) {
                return
            }
            super.log(priority, tag, message, t)
        }
    }
}