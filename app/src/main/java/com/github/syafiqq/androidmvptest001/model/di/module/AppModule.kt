package com.github.syafiqq.androidmvptest001.model.di.module

import android.content.Context
import com.github.syafiqq.androidmvptest001.controller.App
import com.github.syafiqq.androidmvptest001.model.concurrent.SchedulerProvider
import com.github.syafiqq.androidmvptest001.model.concurrent.SchedulerProviderImpl
import com.github.syafiqq.androidmvptest001.model.service.api.identity.IdentityServer
import com.github.syafiqq.androidmvptest001.model.service.api.identity.IdentityServerImpl
import com.github.syafiqq.androidmvptest001.model.service.identity.UserManager
import com.github.syafiqq.androidmvptest001.model.service.identity.UserManagerImpl
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

    @Binds
    @Singleton
    abstract fun provideUserManager(app: UserManagerImpl): UserManager

    @Binds
    @Singleton
    abstract fun provideIdentityServer(app: IdentityServerImpl): IdentityServer
}

@Module
object StaticAppModule