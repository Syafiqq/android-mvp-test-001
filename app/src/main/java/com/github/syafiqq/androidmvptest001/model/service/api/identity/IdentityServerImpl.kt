package com.github.syafiqq.androidmvptest001.model.service.api.identity

import com.github.syafiqq.androidmvptest001.model.dump.UserWebService
import com.github.syafiqq.androidmvptest001.model.entity.UserEntity
import com.github.syafiqq.ext.io.reactivex.MaybeExt
import io.reactivex.Maybe
import javax.inject.Inject

class IdentityServerImpl @Inject constructor() : IdentityServer {

    override fun login(email: String, password: String): Maybe<UserEntity> {
        return Maybe.defer {
            UserWebService.login(email, password).let {
                if (it != null) {
                    MaybeExt.ofNullable(it)
                } else
                    Maybe.error<UserEntity>(RuntimeException("Not Registered"))
            }
        }
    }

    override fun logout() {
        UserWebService.logout()
    }

    override fun isLoggedIn(): Boolean {
        return UserWebService.isLoggedIn()
    }

    override fun getUser(id: String): Maybe<UserEntity> {
        return Maybe.defer {
            try {
                MaybeExt.ofNullable(UserWebService.getUser(id))
            } catch (e: Throwable) {
                Maybe.error<UserEntity>(e)
            }
        }
    }
}