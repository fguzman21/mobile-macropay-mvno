package com.grupomacro.mvno.data.datasource.session.mapper

import com.grupomacro.mvno.database.entity.SessionCredentialsEmbedded
import com.grupomacro.mvno.domain.session.model.SessionCredentials
import com.grupomacro.mvno.domain.user.model.UserCredentialTypeEnum

fun SessionCredentials.toEntity(): SessionCredentialsEmbedded {
    return SessionCredentialsEmbedded(
        userCredential = this.userCredential,
        userCredentialType = this.userCredentialType.name,
        password = this.password
    )
}

fun SessionCredentialsEmbedded.toDomain(): SessionCredentials {
    return SessionCredentials(
        userCredential = this.userCredential,
        userCredentialType = UserCredentialTypeEnum.valueOf(this.userCredentialType),
        password = this.password
    )
}
