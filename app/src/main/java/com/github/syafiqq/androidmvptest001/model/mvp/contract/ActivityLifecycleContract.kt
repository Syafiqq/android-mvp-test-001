package com.github.syafiqq.androidmvptest001.model.mvp.contract

import android.os.Bundle
import android.os.PersistableBundle
import androidx.lifecycle.LifecycleObserver

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
        fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?)
        fun onPostCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?)
        fun onSaveInstanceState(outState: Bundle)
        fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle)
        fun onRestoreInstanceState(savedInstanceState: Bundle)
        fun onRestoreInstanceState(savedInstanceState: Bundle?, persistentState: PersistableBundle?)
    }

    interface Presenter : BaseContract {
        val lifecycleObserver: LifecycleObserver
        fun onCreate()
        fun onCreateWithPersistence()
        fun onPostCreate()
        fun onPostCreateWithPersistence()
        fun onSaveInstanceState()
        fun onSaveInstanceStateWithPersistence()
        fun onRestoreInstanceState()
        fun onRestoreInstanceStateWithPersistence()
    }
}