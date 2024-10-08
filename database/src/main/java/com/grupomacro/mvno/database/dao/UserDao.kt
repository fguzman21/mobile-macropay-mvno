package com.grupomacro.mvno.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.grupomacro.mvno.database.entity.UserEntity

@Dao
interface UserDao {

    @Query("SELECT * FROM user WHERE client_id = :clientId")
    suspend fun get(clientId: String): UserEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: UserEntity): Long

    @Query("SELECT * FROM user")
    suspend fun getAll(): List<UserEntity>
}
