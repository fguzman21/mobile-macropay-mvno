package com.grupomacro.mvno.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.grupomacro.mvno.database.entity.SessionEntity


@Dao
interface SessionDao {

    @Query("SELECT * FROM session WHERE id = :sessionId")
    suspend fun get(sessionId: Long): SessionEntity

    @Query(
        "SELECT * " +
                "FROM session " +
                "WHERE  user_credential = :credential and password = :password " +
                "ORDER BY id DESC " +
                "LIMIT 1"
    )
    suspend fun getLastSession(credential: String, password: String): SessionEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(session: SessionEntity): Long

    @Query("SELECT * from session WHERE end_timestamp = 0")
    suspend fun getActiveSessions(): List<SessionEntity>

    @Query("UPDATE session SET end_timestamp = :currentTime WHERE id in (:ids)")
    suspend fun updateEndTimeForSessions(currentTime: Long, ids: List<Long>)
}
