package com.entin.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.entin.room.dao.UsersDAO
import com.entin.room.model.UserRoom

@Database(entities = [UserRoom::class], version = 1, exportSchema = false)
abstract class Db : RoomDatabase() {

    abstract fun newsDao(): UsersDAO

    companion object {
        const val DATABASE_NAME: String = "TestApplicationUsers"
    }
}