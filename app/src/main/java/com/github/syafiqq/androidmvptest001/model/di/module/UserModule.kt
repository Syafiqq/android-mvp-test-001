package com.github.syafiqq.androidmvptest001.model.di.module

import com.github.syafiqq.androidmvptest001.model.di.scope.UserScope
import com.github.syafiqq.androidmvptest001.model.entity.UserEntity
import com.github.syafiqq.androidmvptest001.model.service.identity.UserManager
import dagger.Module
import dagger.Provides

@Module(includes = [StaticUserModule::class])
abstract class UserModule

@Module
object StaticUserModule {
    @Provides
    @UserScope
    @JvmStatic
    fun provideUser(userManager: UserManager): UserEntity = userManager.getUser().blockingGet()
}