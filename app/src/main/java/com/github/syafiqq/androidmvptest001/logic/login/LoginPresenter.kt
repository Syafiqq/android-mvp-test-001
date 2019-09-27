package com.github.syafiqq.androidmvptest001.logic.login

import android.text.TextUtils
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
) : LoginContract.Presenter {
    var disposable = CompositeDisposable()
    override fun doLogin(email: String?, password: String?) {
        view?.onLoginClick()

        if (TextUtils.isEmpty(email)) {
            view?.onInvalidEmail("Email cannot be empty")
            return
        }
        if (TextUtils.isEmpty(password)) {
            view?.onInvalidEmail("Email cannot be empty")
            return
        }

        val loginApi = api.login(email!!, password!!)
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

    override fun onCreate() {
        Timber.d("onCreate")
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
}