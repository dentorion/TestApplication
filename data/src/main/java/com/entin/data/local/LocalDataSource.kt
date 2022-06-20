package com.entin.data.local

import com.entin.room.model.UserRoom
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    suspend fun saveAllUsers(usersList: List<UserRoom>)

    suspend fun getAllUsers(): Flow<List<UserRoom>>
}