package com.grupomacro.mvno.data.datasource

import com.grupomacro.mvno.data.datasource.session.model.SessionStartData
import com.grupomacro.mvno.domain.core.Either
import com.grupomacro.mvno.domain.session.model.SessionCredentials

interface SessionRemoteDataSource {
    suspend fun newSession(credentials: SessionCredentials): Either<SessionStartData>
}
