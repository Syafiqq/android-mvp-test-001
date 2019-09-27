package com.github.syafiqq.androidmvptest001.model.service.api.identity

import com.github.syafiqq.androidmvptest001.model.entity.UserEntity

interface IdentityServer {
    fun login(email: String, password: String): UserEntity?
    fun logout()
    fun getUser(id: String): UserEntity?
}