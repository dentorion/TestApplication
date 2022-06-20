package com.entin.data.local

import com.entin.room.dao.UsersDAO
import com.entin.room.model.UserRoom
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val usersDao: UsersDAO
) : LocalDataSource {

    override suspend fun saveAllUsers(usersList: List<UserRoom>) {
        usersDao.addUsers(usersList)
    }

    override suspend fun getAllUsers(): List<UserRoom> {
        val result = mutableListOf<UserRoom>()
        usersDao.getAllUsers().collect {
            result.addAll(it)
        }
        return result
    }
}