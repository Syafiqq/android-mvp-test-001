package com.github.syafiqq.androidmvptest001.model.dump

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.syafiqq.androidmvptest001.model.concurrent.SchedulerProvider
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

interface ClockLiveData {
    val hundreds: LiveData<Int>
    val seconds: LiveData<Int>
    val minutes: LiveData<Int>

    fun start()
    fun finish()
    fun reset()
}

class ClockLiveDataImpl @Inject constructor(schedulers: SchedulerProvider) : ClockLiveData {
    private val lock = Any()
    private var obs: Observable<Long> =
        Observable.interval(10, TimeUnit.MILLISECONDS, schedulers.single())

    private val disposable = CompositeDisposable()

    private val _hundreds = MutableLiveData(0)
    private val _seconds = MutableLiveData(0)
    private val _minutes = MutableLiveData(0)
    override val hundreds: LiveData<Int> by lazy {
        _hundreds
    }
    override val seconds: LiveData<Int> by lazy {
        _seconds
    }
    override val minutes: LiveData<Int> by lazy {
        _minutes
    }

    override fun start() {
        reset()
        obs.subscribe(::tick).let(disposable::add)
    }

    private fun tick(interval: Long) {
        val hundred = _hundreds.value ?: -100 + 100
        val second = _seconds.value ?: -1 + if (hundred == 1000) 1 else 0
        val minute = _minutes.value ?: -1 + if (second == 60) 1 else 0

        _hundreds.value = if (hundred == 1000) 0 else hundred
        _seconds.value = if (second == 60) 0 else second
        _minutes.value = minute
    }

    override fun finish() {
        disposable.dispose()
    }

    @Synchronized
    override fun reset() {
        synchronized(lock) {
            _hundreds.value = 0
            _seconds.value = 0
            _minutes.value = 0
        }
    }
}