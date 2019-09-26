package com.github.syafiqq.androidmvptest001.logic.splash

import timber.log.Timber
import javax.inject.Inject

class SplashScreenPresenter : SplashScreenContract.Presenter {
    override fun onCreate() {
        Timber.d("onCreate")
    }

    override fun onStart() {
        Timber.d("onStart")
    }

    override fun onResume() {
        Timber.d("onStart")
    }

    override fun onRestart() {
        Timber.d("onRestart")
    }

    override fun onPause() {
        Timber.d("onPause")
    }

    override fun onStop() {
        Timber.d("onStop")
    }

    override fun onDestroy() {
        Timber.d("onDestroy")
    }

    lateinit var view: SplashScreenContract.View
}