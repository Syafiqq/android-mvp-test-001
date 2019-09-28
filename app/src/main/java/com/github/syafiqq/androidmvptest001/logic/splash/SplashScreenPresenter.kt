package com.github.syafiqq.androidmvptest001.logic.splash

import android.annotation.SuppressLint
import com.github.syafiqq.androidmvptest001.model.concurrent.SchedulerProvider
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject

class SplashScreenPresenter @Inject constructor(
    @JvmField private var view: SplashScreenContract.View?,
    private val schedulers: SchedulerProvider
) : SplashScreenContract.Presenter {
    private var disposable = CompositeDisposable()

    override fun onCreate() {
        Timber.d("onCreate")

        dispatchUi()
    }

    override fun onCreateWithPersistence() {
        Timber.d("onCreateWithPersistence")
    }

    override fun onPostCreate() {
        Timber.d("onPostCreate")
    }

    override fun onPostCreateWithPersistence() {
        Timber.d("onPostCreateWithPersistence")
    }

    override fun onSaveInstanceState() {
        Timber.d("onSaveInstanceState")
    }

    override fun onSaveInstanceStateWithPersistence() {
        Timber.d("onSaveInstanceStateWithPersistence")
    }

    override fun onRestoreInstanceState() {
        Timber.d("onRestoreInstanceState")
    }

    override fun onRestoreInstanceStateWithPersistence() {
        Timber.d("onRestoreInstanceStateWithPersistence")
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

        disposable.dispose()
        view = null
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
            .subscribe { view?.gotoLoginActivity() }
    }
}