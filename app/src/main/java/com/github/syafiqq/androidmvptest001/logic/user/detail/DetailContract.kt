package com.github.syafiqq.androidmvptest001.logic.user.detail

import com.github.syafiqq.androidmvptest001.model.mvp.contract.ActivityLifecycleContract

interface DetailContract {
    interface View : ActivityLifecycleContract.View

    interface Presenter : ActivityLifecycleContract.Presenter
}