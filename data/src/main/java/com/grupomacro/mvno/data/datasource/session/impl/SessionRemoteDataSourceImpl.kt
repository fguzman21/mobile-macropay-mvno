package com.grupomacro.mvno.data.datasource.session.impl

import com.grupomacro.mvno.data.datasource.SessionRemoteDataSource
import com.grupomacro.mvno.data.datasource.session.model.SessionStartData
import com.grupomacro.mvno.data.datasource.user.mapper.network.toDomain
import com.grupomacro.mvno.domain.core.Either
import com.grupomacro.mvno.domain.core.exception.DomainException
import com.grupomacro.mvno.domain.exception.session.MissingUserCredentialsException
import com.grupomacro.mvno.domain.core.exception.NotSupportedUserCredentialsException
import com.grupomacro.mvno.domain.core.exception.RemoteDataSourceException
import com.grupomacro.mvno.domain.exception.session.UserNotFoundRemoteException
import com.grupomacro.mvno.domain.session.model.Session
import com.grupomacro.mvno.domain.session.model.SessionCredentials
import com.grupomacro.mvno.domain.user.model.UserCredentialTypeEnum
import com.grupomacro.mvno.network.ApiFacadeEntryPoint
import com.grupomacro.mvno.network.network.api.login.UserLoginApiFacade
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SessionRemoteDataSourceImpl(
    private val loginApi: UserLoginApiFacade
) : SessionRemoteDataSource {

    @Inject
    constructor() : this(ApiFacadeEntryPoint.instance.userLoginApiFacade)

    override suspend fun newSession(credentials: SessionCredentials): Either<SessionStartData> {
        return withContext(Dispatchers.IO) {
            val userForSession = when (credentials.userCredentialType) {
                UserCredentialTypeEnum.SL -> Result.failure(NotSupportedUserCredentialsException(TAG))
                UserCredentialTypeEnum.MAIL -> loginApi.loginWithMail(credentials.userCredential, credentials.password)
                UserCredentialTypeEnum.PHONE -> loginApi.loginWithPhone(credentials.userCredential, credentials.password)
                UserCredentialTypeEnum.NONE -> Result.failure(MissingUserCredentialsException(TAG))
            }.mapCatching { apiResponse ->
                apiResponse.response.data
                    ?.attributes
                    ?.user
                    ?.toDomain() ?: throw UserNotFoundRemoteException(credentials.userCredential)
            }
            userForSession.fold(
                onSuccess = { user ->
                    val session = Session.freshRemoteSession(user = user, sessionCredentials = credentials)
                    Either.value(SessionStartData(session, user, listOf()))
                },
                onFailure = { throwable ->
                    when (throwable) {
                        is DomainException -> Either.failure(throwable)
                        else -> Either.failure(RemoteDataSourceException("$TAG: ${throwable.message}"))
                    }
                }
            )
        }
    }

    companion object {
        private const val TAG = "SessionRemoteDataSourceImpl"
    }
}
