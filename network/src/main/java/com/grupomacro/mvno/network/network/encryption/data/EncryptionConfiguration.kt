package com.grupomacro.mvno.network.network.encryption.data

data class EncryptionConfiguration(
    val algorithm: String,
    val transformation: String,
    val charset: String,
    val keyString: String
)
