package com.github.syafiqq.androidmvptest001.model.di.module

import com.github.syafiqq.androidmvptest001.logic.home.HomeActivity
import com.github.syafiqq.androidmvptest001.logic.home.HomeModule
import com.github.syafiqq.androidmvptest001.model.di.scope.UserScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UserActivityMapperModule {
    @ContributesAndroidInjector(modules = [HomeModule::class])
    @UserScope
    abstract fun contributeHomeActivityInjector(): HomeActivity
}