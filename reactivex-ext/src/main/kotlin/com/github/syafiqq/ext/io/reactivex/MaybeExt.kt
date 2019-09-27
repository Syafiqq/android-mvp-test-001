package com.github.syafiqq.ext.io.reactivex

import io.reactivex.Maybe

object MaybeExt {
    fun <T> ofNullable(value: T?): Maybe<T> {
        return if (value == null) Maybe.empty() else Maybe.just(value)
    }
}