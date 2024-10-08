package com.grupomacro.mvno.domain.session.repository

import com.grupomacro.mvno.domain.session.model.Session
import com.grupomacro.mvno.domain.session.model.SessionCredentials

interface SessionRepository {

    suspend fun getCurrentSession(): Session

    suspend fun refreshCurrentSession(): Session

    suspend fun newSession(credentials: SessionCredentials, storeSession: Boolean): Session
}
