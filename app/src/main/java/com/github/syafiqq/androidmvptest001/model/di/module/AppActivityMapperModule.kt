package com.github.syafiqq.androidmvptest001.model.di.module

import com.github.syafiqq.androidmvptest001.logic.splash.SplashScreenActivity
import com.github.syafiqq.androidmvptest001.model.di.scope.ActivityScope
import dagger.android.ContributesAndroidInjector

abstract class AppActivityMapperModule {
    @ContributesAndroidInjector
    @ActivityScope
    abstract fun contributeSplashActivityInjector(): SplashScreenActivity
}