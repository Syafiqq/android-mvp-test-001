package com.github.syafiqq.androidmvptest001.model.concurrent

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SchedulerProviderImpl @Inject constructor() :
    SchedulerProvider {
    override fun single(): Scheduler = Schedulers.single()
    override fun computation(): Scheduler = Schedulers.computation()
    override fun io(): Scheduler = Schedulers.io()
    override fun trampoline(): Scheduler = Schedulers.trampoline()
    override fun newThread(): Scheduler = Schedulers.newThread()
    override fun mainThread(): Scheduler = AndroidSchedulers.mainThread()
}