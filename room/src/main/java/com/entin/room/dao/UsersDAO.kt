package com.entin.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.entin.room.model.UserRoom
import kotlinx.coroutines.flow.Flow

/**
 * DAO class for Room
 */

@Dao
interface UsersDAO {
    @Query("SELECT * FROM users")
    fun getAllUsers(): Flow<List<UserRoom>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUsers(usersList: List<UserRoom>)
}