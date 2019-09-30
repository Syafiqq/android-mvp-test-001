package com.github.syafiqq.androidmvptest001.logic.login

import com.github.syafiqq.androidmvptest001.model.dump.ClockLiveData
import com.github.syafiqq.androidmvptest001.model.entity.UserEntity
import com.github.syafiqq.androidmvptest001.model.mvp.contract.ActivityLifecycleContract

interface LoginContract {
    interface View : ActivityLifecycleContract.View {
        fun onLoginClick()
        fun onResetValidation()
        fun onInvalidEmail(cause: String)
        fun onInvalidPassword(cause: String)
        fun onSuccessLogin(user: UserEntity)
        fun onFailedLogin(cause: String)
        fun onErrorLogin(ex: Throwable)
        fun onCompleteLogin()
        fun onCancelLogin()
        fun gotoHomeActivity()
    }

    interface Presenter : ActivityLifecycleContract.Presenter {
        val clock: ClockLiveData
        fun doLogin(request: LoginRequest)
        fun doCancelLogin()
    }
}