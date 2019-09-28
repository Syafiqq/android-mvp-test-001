package com.github.syafiqq.androidmvptest001.logic.user.home

import timber.log.Timber
import javax.inject.Inject

class HomePresenter @Inject constructor(
    @JvmField private var view: HomeContract.View?
) : HomeContract.Presenter {
    override fun onCreate() {
        Timber.d("onCreate")
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

        view = null
    }

    override fun onMailClicked() {
        Timber.d("onMailClicked")

        view?.gotoDetailActivity()
    }
}