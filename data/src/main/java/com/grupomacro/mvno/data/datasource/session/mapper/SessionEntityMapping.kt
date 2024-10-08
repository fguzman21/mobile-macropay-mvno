package com.grupomacro.mvno.data.datasource.session.mapper

import com.grupomacro.mvno.database.entity.SessionEntity
import com.grupomacro.mvno.domain.session.model.Session
import com.grupomacro.mvno.domain.user.model.User

fun Session.toEntity(): SessionEntity {
    return SessionEntity(
        id = this.id,
        userId = this.user.clientId,
        sessionCredentials = this.sessionCredentials.toEntity(),
        startTimestamp = this.startTimestamp,
        endTimeStamp = this.endTimeStamp,
        isOnlineValidated = this.isOnlineValidated
    )
}

fun SessionEntity.toDomain(user: User): Session {
    return Session(
        id = this.id,
        user = user,
        sessionCredentials = this.sessionCredentials.toDomain(),
        startTimestamp = 0L,
        endTimeStamp = 0L,
        isOnlineValidated = false
    )
}
