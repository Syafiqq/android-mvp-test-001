package com.github.syafiqq.androidmvptest001.model.service.identity

import com.github.syafiqq.androidmvptest001.model.entity.UserEntity
import io.reactivex.Maybe

interface UserManager {
    fun setSession(user: UserEntity)
    fun destroySession()
    fun isLoggedIn(): Boolean
    fun getUser(): Maybe<UserEntity>
}