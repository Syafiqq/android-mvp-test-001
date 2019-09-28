package com.github.syafiqq.androidmvptest001.logic.user.detail

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import timber.log.Timber
import javax.inject.Inject

class DetailPresenter @Inject constructor(
    @JvmField private var view: DetailContract.View?
) : DetailContract.Presenter, LifecycleObserver {
    override val lifecycleObserver: LifecycleObserver = this

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
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

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    override fun onStart() {
        Timber.d("onStart")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    override fun onResume() {
        Timber.d("onResume")
    }

    override fun onRestart() {
        Timber.d("onRestart")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    override fun onPause() {
        Timber.d("onPause")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    override fun onStop() {
        Timber.d("onStop")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    override fun onDestroy() {
        Timber.d("onDestroy")

        view = null
    }
}