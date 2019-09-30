package com.github.syafiqq.androidmvptest001.model.di.module

import com.github.syafiqq.androidmvptest001.model.dump.ClockLiveData
import com.github.syafiqq.androidmvptest001.model.dump.ClockLiveDataImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module(includes = [StaticAppLiveDataModule::class])
abstract class AppLiveDataModule {
    @Binds
    @Singleton
    abstract fun provideIdentityServer(app: ClockLiveDataImpl): ClockLiveData
}

@Module
object StaticAppLiveDataModule