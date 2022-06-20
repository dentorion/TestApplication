package com.entin.data.utils

import com.entin.data.model.apiOne.ApiOneResponseItem
import com.entin.data.model.apiTwo.ApiTwoResponse
import com.entin.domain.model.UserDomain
import com.entin.room.model.UserRoom

/**
 * Mapper
 * User models converter.
 */

fun ApiOneResponseItem.mapToRoomModel(): UserRoom =
    UserRoom(
        nameUser = this.screenname,
        avatarUrl = DEFAULT_AVATAR_URL,
        source = API_ONE_NAME
    )

fun ApiTwoResponse.mapToRoomModel(): UserRoom =
    UserRoom(
        nameUser = this.login,
        avatarUrl = this.avatar_url,
        source = API_TWO_NAME,
    )

fun UserRoom.mapToDomainModel(): UserDomain =
    UserDomain(
        name = this.nameUser,
        avatarUrl = this.avatarUrl,
        source = this.source,
    )

const val DEFAULT_AVATAR_URL = "https://innostudio.de/fileuploader/images/default-avatar.png"
const val API_ONE_NAME = "Dailymotion"
const val API_TWO_NAME = "GitHub"
