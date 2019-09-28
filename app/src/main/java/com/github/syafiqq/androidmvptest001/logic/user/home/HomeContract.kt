package com.github.syafiqq.androidmvptest001.logic.user.home

import com.github.syafiqq.androidmvptest001.model.entity.UserEntity
import com.github.syafiqq.androidmvptest001.model.mvp.contract.ActivityLifecycleContract

interface HomeContract {
    interface View : ActivityLifecycleContract.View {
        fun gotoDetailActivity()
    }

    interface Presenter : ActivityLifecycleContract.Presenter {
        val user: UserEntity
        fun onMailClicked()
    }
}