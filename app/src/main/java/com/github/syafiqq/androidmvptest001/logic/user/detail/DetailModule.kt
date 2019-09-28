package com.github.syafiqq.androidmvptest001.logic.user.detail

import dagger.Binds
import dagger.Module

@Module(includes = [StaticDetailModule::class])
abstract class DetailModule {
    @Binds
    abstract fun provideView(view: DetailActivity): DetailContract.View

    @Binds
    abstract fun providePresenter(presenter: DetailPresenter): DetailContract.Presenter
}

@Module
object StaticDetailModule