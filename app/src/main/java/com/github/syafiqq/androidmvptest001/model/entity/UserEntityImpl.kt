package com.github.syafiqq.androidmvptest001.model.entity

data class UserEntityImpl(
    override var id: String,
    override var name: String,
    override var email: String,
    override var password: String
) : UserEntity