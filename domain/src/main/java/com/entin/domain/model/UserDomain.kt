package com.entin.domain.model

import java.io.Serializable

data class UserDomain(
    val name: String,
    val avatarUrl: String = "https://innostudio.de/fileuploader/images/default-avatar.png",
    val source: String,
): Serializable
