package com.github.syafiqq.androidmvptest001.model.di.component

import com.github.syafiqq.androidmvptest001.controller.App
import com.github.syafiqq.androidmvptest001.model.di.module.AppActivityMapperModule
import com.github.syafiqq.androidmvptest001.model.di.module.AppModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        AppActivityMapperModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {
    @Component.Factory
    interface Factory : AndroidInjector.Factory<App>
}