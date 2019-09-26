package com.github.syafiqq.androidmvptest001.logic.splash

import dagger.Binds
import dagger.Module

@Module(includes = [StaticSplashScreenModule::class])
abstract class SplashScreenModule {
    @Binds
    abstract fun provideView(view: SplashScreenActivity): SplashScreenContract.View

    @Binds
    abstract fun providePresenter(presenter: SplashScreenPresenter): SplashScreenContract.Presenter
}

@Module
object StaticSplashScreenModule