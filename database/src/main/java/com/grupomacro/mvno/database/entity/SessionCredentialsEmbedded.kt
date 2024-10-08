package com.grupomacro.mvno.database.entity

import androidx.room.ColumnInfo

data class SessionCredentialsEmbedded(

    @ColumnInfo("user_credential")
    val userCredential: String,

    @ColumnInfo("user_credential_type")
    val userCredentialType: String,

    @ColumnInfo("password")
    val password: String,
)
