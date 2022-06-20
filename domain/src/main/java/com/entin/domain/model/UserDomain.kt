package com.entin.domain.model

import java.io.Serializable

data class UserDomain(
    val name: String,
    val avatarUrl: String = "some default address",
    val source: String,
): Serializable
