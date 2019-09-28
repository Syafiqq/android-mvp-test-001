package com.github.syafiqq.androidmvptest001.model.di.module

import com.github.syafiqq.androidmvptest001.logic.user.home.HomeActivity
import com.github.syafiqq.androidmvptest001.logic.user.home.HomeModule
import com.github.syafiqq.androidmvptest001.model.di.scope.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UserActivityMapperModule {
    @ContributesAndroidInjector(modules = [HomeModule::class])
    @ActivityScope
    abstract fun contributeHomeActivityInjector(): HomeActivity
}