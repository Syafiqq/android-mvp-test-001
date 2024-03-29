package com.github.syafiqq.androidmvptest001.model.di.module

import com.github.syafiqq.androidmvptest001.logic.login.LoginActivity
import com.github.syafiqq.androidmvptest001.logic.login.LoginModule
import com.github.syafiqq.androidmvptest001.logic.splash.SplashScreenActivity
import com.github.syafiqq.androidmvptest001.logic.splash.SplashScreenModule
import com.github.syafiqq.androidmvptest001.model.di.scope.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppActivityMapperModule {
    @ContributesAndroidInjector(modules = [SplashScreenModule::class])
    @ActivityScope
    abstract fun contributeSplashActivityInjector(): SplashScreenActivity

    @ContributesAndroidInjector(modules = [LoginModule::class])
    @ActivityScope
    abstract fun contributeLoginActivityInjector(): LoginActivity
}