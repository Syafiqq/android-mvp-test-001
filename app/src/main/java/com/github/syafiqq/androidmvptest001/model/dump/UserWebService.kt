package com.github.syafiqq.androidmvptest001.model.dump

import android.text.TextUtils
import com.github.syafiqq.androidmvptest001.model.entity.UserEntity
import com.github.syafiqq.androidmvptest001.model.entity.UserEntityImpl


object UserWebService {
    private var session: UserEntity? = null
    private val users: Map<String, UserEntity> by lazy {
        mapOf(
            "1" to UserEntityImpl("1", "name1", "mail1@mail.com", "pass1"),
            "2" to UserEntityImpl("2", "name2", "mail2@mail.com", "pass2"),
            "3" to UserEntityImpl("3", "name3", "mail3@mail.com", "pass3"),
            "4" to UserEntityImpl("4", "name4", "mail4@mail.com", "pass4")
        )
    }

    fun login(email: String, password: String): UserEntity? {
        Thread.sleep(1000)
        return users.values.firstOrNull {
            TextUtils.equals(email, it.email) and TextUtils.equals(password, it.password)
        }
    }

    fun logout() {
        Thread.sleep(1000)
        session = null
    }

    fun isLoggedIn(): Boolean {
        Thread.sleep(1000)
        return session == null
    }

    fun getUser(id: String): UserEntity? {
        Thread.sleep(1000)
        return if (session == null)
            throw RuntimeException("Unauthorized")
        else
            users.entries.firstOrNull {
                TextUtils.equals(id, it.key)
            }?.value
    }
}