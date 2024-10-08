package com.grupomacro.mvno.data.datasource

import com.grupomacro.mvno.domain.core.Either
import com.grupomacro.mvno.domain.session.model.Session
import com.grupomacro.mvno.domain.session.model.SessionCredentials


interface SessionLocalDataSource {
    suspend fun lastStoredSession(credentials: SessionCredentials): Either<Session>
    suspend fun activeSession(): Either<Session?>
    suspend fun saveSession(session: Session): Either<Session>
    suspend fun closeActiveSessions(): Either<Unit>
}
