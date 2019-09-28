package com.github.syafiqq.androidmvptest001.logic.login

import android.text.TextUtils
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.github.syafiqq.androidmvptest001.model.concurrent.SchedulerProvider
import com.github.syafiqq.androidmvptest001.model.entity.UserEntity
import com.github.syafiqq.androidmvptest001.model.service.api.identity.IdentityServer
import com.github.syafiqq.androidmvptest001.model.service.identity.UserManager
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import timber.log.Timber
import javax.inject.Inject


class LoginPresenter @Inject constructor(
    @JvmField private var view: LoginContract.View?,
    private val api: IdentityServer,
    private val session: UserManager,
    private val scheduler: SchedulerProvider
) : LoginContract.Presenter, LifecycleObserver {
    override val lifecycleObserver: LifecycleObserver = this

    private var disposable = CompositeDisposable()

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

        disposable.dispose()
        view = null
    }

    override fun doLogin(request: LoginRequest) {
        view?.onLoginClick()

        if (TextUtils.isEmpty(request.email)) {
            view?.onInvalidEmail("Email cannot be empty")
            return
        }
        if (TextUtils.isEmpty(request.password)) {
            view?.onInvalidEmail("Email cannot be empty")
            return
        }

        val loginApi = api.login(request.email, request.password)
            .subscribeOn(scheduler.single())
            .observeOn(scheduler.ui())
            .subscribe(::onLoginSuccess, ::onLoginError, ::onLoginComplete)

        loginApi.addTo(disposable)
    }

    private fun onLoginSuccess(user: UserEntity) {
        Timber.d("onLoginSuccess [UserEntity]")
        session.setSession(user)
        view?.onSuccessLogin(user)
        view?.gotoHomeActivity()
    }

    private fun onLoginError(e: Throwable) {
        Timber.d("onLoginError [Throwable]")
        view?.onErrorLogin(e)
    }

    private fun onLoginComplete() {
        Timber.d("onLoginComplete [Throwable]")

        view?.onCompleteLogin()
    }

    override fun doCancelLogin() {
        Timber.d("doCancelLogin")
    }
}