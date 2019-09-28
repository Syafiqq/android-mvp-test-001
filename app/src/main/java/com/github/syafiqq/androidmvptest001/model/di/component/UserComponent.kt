package com.github.syafiqq.androidmvptest001.model.di.component

import com.github.syafiqq.androidmvptest001.controller.App
import com.github.syafiqq.androidmvptest001.model.di.module.UserActivityMapperModule
import com.github.syafiqq.androidmvptest001.model.di.module.UserModule
import com.github.syafiqq.androidmvptest001.model.di.scope.UserScope
import dagger.Subcomponent
import dagger.android.AndroidInjector

@UserScope
@Subcomponent(
    modules = [
        UserActivityMapperModule::class,
        UserModule::class
    ]
)
interface UserComponent : AndroidInjector<App> {
    @Subcomponent.Factory
    interface Factory : AndroidInjector.Factory<App>
}