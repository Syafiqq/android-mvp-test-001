package com.github.syafiqq.androidmvptest001.logic.home

import dagger.Binds
import dagger.Module

@Module(includes = [StaticHomeModule::class])
abstract class HomeModule {
    @Binds
    abstract fun provideView(view: HomeActivity): HomeContract.View

    @Binds
    abstract fun providePresenter(presenter: HomePresenter): HomeContract.Presenter
}

@Module
object StaticHomeModule