package com.entin.room.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.entin.room.model.UserRoom.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class UserRoom(

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "name_user")
    val nameUser: String,
    @ColumnInfo(name = "avatar_url")
    val avatarUrl: String,
    @ColumnInfo(name = "source")
    val source: String,
) {

    companion object {
        const val TABLE_NAME = "users"
    }
}