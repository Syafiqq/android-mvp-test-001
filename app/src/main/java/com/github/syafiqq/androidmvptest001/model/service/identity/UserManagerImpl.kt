package com.github.syafiqq.androidmvptest001.model.service.identity

import com.github.syafiqq.androidmvptest001.model.entity.UserEntity
import com.github.syafiqq.ext.io.reactivex.MaybeExt
import io.reactivex.Maybe
import javax.inject.Inject

class UserManagerImpl @Inject constructor() : UserManager {
    private var session: UserEntity? = null

    override fun setSession(user: UserEntity) {
        session = user
    }

    override fun destroySession() {
        session = null
    }

    override fun isLoggedIn(): Boolean = session == null

    override fun getUser(): Maybe<UserEntity> = MaybeExt.ofNullable(session)
}