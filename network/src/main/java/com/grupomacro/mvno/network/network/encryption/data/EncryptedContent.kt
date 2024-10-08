package com.grupomacro.mvno.network.network.encryption.data

import com.google.gson.annotations.SerializedName


data class EncryptedContent(

    @SerializedName("iv")
    val iv: String,

    @SerializedName("encrypted")
    val encrypted: String
)
