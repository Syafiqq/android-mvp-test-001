package com.github.syafiqq.androidmvptest001.model.concurrent

import io.reactivex.Scheduler

interface SchedulerProvider {
    fun single(): Scheduler
    fun computation(): Scheduler
    fun io(): Scheduler
    fun trampoline(): Scheduler
    fun newThread(): Scheduler
    fun mainThread(): Scheduler
    fun ui(): Scheduler = mainThread()
}