package com.grupomacro.mvno.data.repository

import com.grupomacro.mvno.data.datasource.SessionLocalDataSource
import com.grupomacro.mvno.data.datasource.SessionRemoteDataSource
import com.grupomacro.mvno.data.datasource.session.model.SessionStartData
import com.grupomacro.mvno.data.datasource.user.UserLocalDataSource
import com.grupomacro.mvno.domain.core.runAndCatch
import com.grupomacro.mvno.domain.exception.session.SessionNotFoundException
import com.grupomacro.mvno.domain.exception.session.UserNotFoundLocalException
import com.grupomacro.mvno.domain.exception.session.UserNotFoundRemoteException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.grupomacro.mvno.domain.session.repository.SessionRepository
import com.grupomacro.mvno.domain.session.model.Session
import com.grupomacro.mvno.domain.session.model.SessionCredentials
import com.grupomacro.mvno.domain.user.model.User
import com.grupomacro.mvno.domain.user.model.UserCredentialTypeEnum
import javax.inject.Inject

class SessionRepositoryImpl @Inject constructor(
    private val sessionRemoteDataSource: SessionRemoteDataSource,
    private val sessionLocalDataSource: SessionLocalDataSource,
    private val userLocalDataSource: UserLocalDataSource,
) : SessionRepository {

    override suspend fun getCurrentSession(): Session {
        return withContext(Dispatchers.IO) {
            val activeSession = sessionLocalDataSource.activeSession().getOrThrow()
            activeSession ?: throw SessionNotFoundException()
        }
    }

    override suspend fun refreshCurrentSession(): Session {
        return withContext(Dispatchers.IO) {
            //TODO("Pending Implementation")
            runAndCatch {
                sessionLocalDataSource
            }
            Session(
                1,
                User.dummy,
                SessionCredentials("algo", UserCredentialTypeEnum.PHONE, "123"),
                3,
                4,
                true,
            )
        }
    }

    override suspend fun newSession(credentials: SessionCredentials, storeSession: Boolean): Session {
        return withContext(Dispatchers.IO) {
            val sessionStartData = sessionRemoteDataSource.newSession(credentials)
            if (sessionStartData.isValue) {
                createNewSession(sessionStartData.getValue(), credentials)
            } else {
                createNewSessionFromPreviousSavedSession(credentials)
            }
        }
    }

    private suspend fun createNewSession(sessionData: SessionStartData, credentials: SessionCredentials): Session {
        return withContext(Dispatchers.IO) {
            if (sessionData.user != null) {
                sessionLocalDataSource.closeActiveSessions()
                userLocalDataSource.addOrUpdateUser(sessionData.user)
                sessionLocalDataSource.saveSession(sessionData.session).getOrThrow()
            } else {
                throw UserNotFoundRemoteException(credentials.userCredential)
            }
        }
    }

    private suspend fun createNewSessionFromPreviousSavedSession(credentials: SessionCredentials): Session {
        return withContext(Dispatchers.IO) {
            val storedSession = sessionLocalDataSource.lastStoredSession(credentials)
            if (storedSession.isValue) {
                val newLocalSession = storedSession.getValue().copy(isOnlineValidated = false)//update timestamps
                sessionLocalDataSource.saveSession(newLocalSession).getOrThrow()
            } else {
                throw UserNotFoundLocalException(credentials.userCredential)
            }
        }
    }
}
