package com.github.syafiqq.androidmvptest001.logic.login

import dagger.Binds
import dagger.Module

@Module(includes = [StaticLoginModule::class])
abstract class LoginModule {
    @Binds
    abstract fun provideView(view: LoginActivity): LoginContract.View

    @Binds
    abstract fun providePresenter(presenter: LoginPresenter): LoginContract.Presenter
}

@Module
object StaticLoginModule