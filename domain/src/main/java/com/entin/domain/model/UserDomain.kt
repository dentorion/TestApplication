package com.entin.domain.model

data class UserDomain(
    val name: String,
    val avatarUrl: String = "some default address",
    val source: String,
)
