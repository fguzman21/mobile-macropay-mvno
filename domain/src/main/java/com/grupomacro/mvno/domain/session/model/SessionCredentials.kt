package com.grupomacro.mvno.domain.session.model

import com.grupomacro.mvno.domain.user.model.UserCredentialTypeEnum

data class SessionCredentials(
    val userCredential: String,
    val userCredentialType: UserCredentialTypeEnum,
    val password: String,
)
