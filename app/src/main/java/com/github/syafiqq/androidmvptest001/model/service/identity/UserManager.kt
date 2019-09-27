package com.github.syafiqq.androidmvptest001.model.service.identity

import com.github.syafiqq.androidmvptest001.model.entity.UserEntity
import io.reactivex.Flowable

interface UserManager {
    fun login(email: String, password: String): Flowable<UserEntity>
    fun logout(): Flowable<UserEntity>
    fun isLoggedIn(): Boolean
    fun getUser(id: String): Flowable<UserEntity>
}