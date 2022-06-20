package com.entin.data.local

import com.entin.room.model.UserRoom

interface LocalDataSource {

    suspend fun saveAllUsers(usersList: List<UserRoom>)

    suspend fun getAllUsers(): List<UserRoom>
}