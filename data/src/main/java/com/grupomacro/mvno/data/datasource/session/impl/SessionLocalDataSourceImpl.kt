package com.grupomacro.mvno.data.datasource.session.impl

import android.util.Log
import com.grupomacro.mvno.data.datasource.SessionLocalDataSource
import com.grupomacro.mvno.data.datasource.session.mapper.toDomain
import com.grupomacro.mvno.data.datasource.session.mapper.toEntity
import com.grupomacro.mvno.data.datasource.user.mapper.database.toDomain
import com.grupomacro.mvno.data.datasource.user.mapper.database.toEntity
import com.grupomacro.mvno.database.DatabaseEntryPoint
import com.grupomacro.mvno.database.dao.SessionDao
import com.grupomacro.mvno.database.dao.UserDao
import com.grupomacro.mvno.database.entity.SessionEntity
import com.grupomacro.mvno.domain.core.Either
import com.grupomacro.mvno.domain.core.collection.hasOne
import com.grupomacro.mvno.domain.core.collection.hasZero
import com.grupomacro.mvno.domain.core.exception.DomainException
import com.grupomacro.mvno.domain.exception.session.MultipleActiveSessionsFoundException
import com.grupomacro.mvno.domain.exception.session.SessionCannotBeSavedException
import com.grupomacro.mvno.domain.exception.session.SessionNotFoundException
import com.grupomacro.mvno.domain.exception.session.SessionsCannotBeClosedException
import com.grupomacro.mvno.domain.exception.session.UserNotFoundLocalException
import com.grupomacro.mvno.domain.session.model.Session
import com.grupomacro.mvno.domain.session.model.SessionCredentials
import javax.inject.Inject

class SessionLocalDataSourceImpl(
    private val sessionDao: SessionDao,
    private val userDao: UserDao,
) : SessionLocalDataSource {

    @Inject
    constructor() : this(DatabaseEntryPoint.instance.sessionDao, DatabaseEntryPoint.instance.userDao)

    override suspend fun lastStoredSession(credentials: SessionCredentials): Either<Session> {
        return try {
            val localSession = sessionDao.getLastSession(credentials.userCredential, credentials.password)
            val localUser = userDao.get(localSession.userId)?.toDomain()
            if (localUser != null) {
                Either.value(sessionDao.get(sessionId = 0L).toDomain(localUser))
            } else {
                Either.failure(UserNotFoundLocalException("User[${credentials.userCredential}]"))
            }
        } catch (e: Exception) {
            Log.e(TAG, e.stackTraceToString())
            Either.failure(DomainException(e))
        }.also {
            Log.i(TAG, "lastStoredSession(${credentials}): $it")
        }
    }

    override suspend fun activeSession(): Either<Session> {
        return try {
            val activeSessions = sessionDao.getActiveSessions()
            when {
                activeSessions.hasZero() -> Either.failure(SessionNotFoundException())
                activeSessions.hasOne() -> getSession(activeSessions.first())
                else -> Either.failure(MultipleActiveSessionsFoundException())
            }
        } catch (e: Exception) {
            Either.failure(SessionNotFoundException(e.message ?: e.javaClass.simpleName))
        }.also {
            Log.i(TAG, "activeSession(): $it")
        }
    }

    override suspend fun saveSession(session: Session): Either<Session> {
        return try {
            val sessionEntity = session.toEntity()
            val idNewSession = sessionDao.insert(sessionEntity)
            val idExistingUser = userDao.get(session.user.clientId)
            if (idExistingUser == null) {
                userDao.insert(session.user.toEntity())
            }
            val updatedSession = sessionDao.get(idNewSession).toDomain(session.user)
            Either.value(updatedSession)
        } catch (e: Exception) {
            Log.e(TAG, e.stackTraceToString())
            Either.failure(SessionCannotBeSavedException(e.message ?: e.javaClass.simpleName))
        }.also {
            Log.i(TAG, "saveSession(${session}): $it")
        }
    }

    override suspend fun closeActiveSessions(): Either<Unit> {
        return try {
            val activeSessionsId = sessionDao.getActiveSessions().map { it.id }
            sessionDao.updateEndTimeForSessions(System.currentTimeMillis(), activeSessionsId)
            Either.value(Unit)
        } catch (e: Exception) {
            Either.failure(SessionsCannotBeClosedException(e.message ?: e.javaClass.simpleName))
        }.also {
            Log.i(TAG, "closeActiveSessions(): $it")
        }
    }

    private suspend fun getSession(activeSession: SessionEntity): Either<Session> {
        val user = userDao.get(activeSession.userId)?.toDomain()
        return if (user != null) {
            Either.value(activeSession.toDomain(user))
        } else {
            Either.failure(UserNotFoundLocalException("User[${activeSession.userId}]"))
        }
    }

    companion object {
        private const val TAG = "SessionLocalDataSourceImpl"
    }
}