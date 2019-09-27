package com.github.syafiqq.androidmvptest001.model.service.api.identity

import android.os.SystemClock
import android.text.TextUtils
import com.github.syafiqq.androidmvptest001.model.dump.Storage
import com.github.syafiqq.androidmvptest001.model.entity.UserEntity
import javax.inject.Inject

class IdentityServerImpl @Inject constructor(): IdentityServer {
    val users = Storage.users
    var session: UserEntity? = null

    override fun login(email: String, password: String): UserEntity? {
        SystemClock.sleep(1000)
        return users.values.firstOrNull {
            TextUtils.equals(email, it.email) and TextUtils.equals(password, it.password)
        }
    }

    override fun logout() {
        SystemClock.sleep(1000)
        session = null
    }

    override fun getUser(id: String): UserEntity? {
        SystemClock.sleep(1000)
        return if (session == null)
            throw RuntimeException("Unauthorized")
        else
            users.entries.firstOrNull {
                TextUtils.equals(id, it.key)
            }?.value
    }
}