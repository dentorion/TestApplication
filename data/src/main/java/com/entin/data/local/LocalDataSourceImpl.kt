package com.entin.data.local

import com.entin.room.dao.UsersDAO
import com.entin.room.model.UserRoom
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val usersDao: UsersDAO
) : LocalDataSource {

    override suspend fun saveAllUsers(usersList: List<UserRoom>) {
        usersDao.addUsers(usersList)
    }

    override suspend fun getAllUsers(): Flow<List<UserRoom>> =
        usersDao.getAllUsers()

}