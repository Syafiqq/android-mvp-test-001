package com.github.syafiqq.androidmvptest001.model.mvp.contract

import android.os.Bundle

interface ActivityLifecycleContract {
    interface BaseContract {
        fun onStart()
        fun onResume()
        fun onRestart()
        fun onPause()
        fun onStop()
        fun onDestroy()
    }

    interface View : BaseContract {
        fun onCreate(savedInstanceState: Bundle?)
    }

    interface Presenter : BaseContract {
        fun onCreate()
    }
}