package com.grupomacro.mvno.domain.session.model

import com.grupomacro.mvno.domain.user.model.User


data class Session(
    val id: Long,
    val user: User,
    val sessionCredentials: SessionCredentials,
    val startTimestamp: Long,
    val endTimeStamp: Long,
    val isOnlineValidated: Boolean,
) {
    companion object {
        fun freshRemoteSession(
            user: User,
            sessionCredentials: SessionCredentials,
        ) = Session(
            id = 0,
            user = user,
            sessionCredentials = sessionCredentials,
            startTimestamp = System.currentTimeMillis(),
            endTimeStamp = 0,
            isOnlineValidated = true,
        )
    }
}
