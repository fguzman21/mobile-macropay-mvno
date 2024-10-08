@file:Suppress("TooManyFunctions")

package com.grupomacro.mvno.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.grupomacro.mvno.database.dao.SessionDao
import com.grupomacro.mvno.database.dao.UserDao
import com.grupomacro.mvno.database.entity.SessionEntity
import com.grupomacro.mvno.database.entity.UserEntity

@Database(
    version = 1,
    entities = [
        SessionEntity::class,
        UserEntity::class
    ],
    exportSchema = false,
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getSessionDao(): SessionDao

    abstract fun getUserDao(): UserDao

}
