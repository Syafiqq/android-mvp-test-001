package com.github.syafiqq.androidmvptest001.logic.splash

import com.github.syafiqq.androidmvptest001.model.mvp.contract.ActivityLifecycleContract

interface SplashScreenContract {
    interface View : ActivityLifecycleContract.View {
        fun gotoLoginActivity()
    }

    interface Presenter : ActivityLifecycleContract.Presenter
}