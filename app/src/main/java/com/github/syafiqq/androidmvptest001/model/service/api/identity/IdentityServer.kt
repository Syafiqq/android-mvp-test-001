package com.github.syafiqq.androidmvptest001.model.service.api.identity

import com.github.syafiqq.androidmvptest001.model.entity.UserEntity
import io.reactivex.Maybe

interface IdentityServer {
    fun login(email: String, password: String): Maybe<UserEntity>
    fun logout()
    fun isLoggedIn(): Boolean
    fun getUser(id: String): Maybe<UserEntity>
}