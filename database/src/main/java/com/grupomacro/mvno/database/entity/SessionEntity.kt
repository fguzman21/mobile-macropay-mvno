package com.grupomacro.mvno.database.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "session"
)
data class SessionEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long,

    @ColumnInfo("user_id")
    val userId: String,

    @Embedded
    val sessionCredentials: SessionCredentialsEmbedded,

    @ColumnInfo("start_timestamp")
    val startTimestamp: Long,

    @ColumnInfo("end_timestamp")
    val endTimeStamp: Long,

    @ColumnInfo("is_online_validated")
    val isOnlineValidated: Boolean,
)
