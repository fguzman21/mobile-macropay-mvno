package com.grupomacro.mvno.domain.user.model

data class UserEmailCollisionItem(
    val userName: String,
    val userSl: String,
    val userCurp: String,
    val isUserRegistered: Boolean,
)
