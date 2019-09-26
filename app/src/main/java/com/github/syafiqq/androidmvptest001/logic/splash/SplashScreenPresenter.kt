package com.github.syafiqq.androidmvptest001.logic.splash

import android.annotation.SuppressLint
import com.github.syafiqq.androidmvptest001.model.concurrent.SchedulerProvider
import io.reactivex.Observable
import io.reactivex.ObservableSource
import timber.log.Timber
import javax.inject.Inject

class SplashScreenPresenter @Inject constructor(
    private val view: SplashScreenContract.View,
    private val schedulers: SchedulerProvider
) : SplashScreenContract.Presenter {
    override fun onCreate() {
        Timber.d("onCreate")

        dispatchUi()
    }

    override fun onStart() {
        Timber.d("onStart")
    }

    override fun onResume() {
        Timber.d("onResume")
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

    private fun dummyWaiting(): ObservableSource<Int> {
        Timber.d("dummyWaiting []")
        Thread.sleep(500)
        return Observable.just(1)
    }

    @SuppressLint("CheckResult")
    private fun dispatchUi() {
        Observable.defer(::dummyWaiting)
            .subscribeOn(schedulers.single())
            .observeOn(schedulers.ui())
            .subscribe { view.gotoLoginActivity() }
    }
}