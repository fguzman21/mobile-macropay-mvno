package com.grupomacro.mvno.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "user"
)
data class UserEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long,

    @ColumnInfo("client_id")
    val clientId: String,

    @ColumnInfo("contract_id")
    val contractId: String,

    @ColumnInfo("sl")
    val sl: String,

    @ColumnInfo("full_name")
    val fullName: String,

    @ColumnInfo("preferred_name")
    val preferredName: String,

    @ColumnInfo("email")
    val email: String,

    @ColumnInfo("curp")
    val curp: String,

    @ColumnInfo("associated_sl")
    val associatedSl: String,
)
