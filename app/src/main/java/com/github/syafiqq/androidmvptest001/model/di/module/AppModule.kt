package com.github.syafiqq.androidmvptest001.model.di.module

import android.content.Context
import com.github.syafiqq.androidmvptest001.controller.App
import com.github.syafiqq.androidmvptest001.model.concurrent.SchedulerProvider
import com.github.syafiqq.androidmvptest001.model.concurrent.SchedulerProviderImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module(includes = [StaticAppModule::class])
abstract class AppModule {
    @Binds
    @Singleton
    abstract fun provideScheduler(provider: SchedulerProviderImpl): SchedulerProvider

    @Binds
    @Singleton
    abstract fun provideApplicationContext(app: App): Context
}

@Module
object StaticAppModule