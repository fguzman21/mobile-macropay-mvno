package com.grupomacro.mvno.domain.user.model

data class User(
    val userId: Long,
    val clientId: String,
    val contractId: String,
    val sl: String,
    val preferredName: String,
    val fullName: String,
    val email: String,
    val curp: String,
    val associatedSl: String,
) {
    companion object {
        val dummy = User(1, "", "", "", "", "", "", "", "")
    }
}
