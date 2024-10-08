package com.grupomacro.mvno.data.datasource.user.impl

import com.grupomacro.mvno.data.datasource.user.UserLocalDataSource
import com.grupomacro.mvno.data.datasource.user.mapper.database.toDomain
import com.grupomacro.mvno.data.datasource.user.mapper.database.toEntity
import com.grupomacro.mvno.database.DatabaseEntryPoint
import com.grupomacro.mvno.database.dao.UserDao
import com.grupomacro.mvno.domain.core.Either
import com.grupomacro.mvno.domain.exception.session.UserNotFoundLocalException
import com.grupomacro.mvno.domain.user.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserLocalDataSourceImpl(
    private val userDao: UserDao
) : UserLocalDataSource {

    @Inject
    constructor() : this(DatabaseEntryPoint.instance.userDao)

    override suspend fun getUser(userId: String): Either<User> {
        return withContext(Dispatchers.IO) {
            val user = userDao.get(userId)?.toDomain()
            if (user != null) {
                Either.value(user)
            } else {
                Either.failure(UserNotFoundLocalException("User[$userId]"))
            }
        }
    }

    override suspend fun addOrUpdateUser(user: User): Either<User> {
        return withContext(Dispatchers.IO) {
            val inserted = userDao.insert(user.toEntity())
            if (inserted > 0) {
                getUser(user.clientId)
            } else {
                Either.failure(UserNotFoundLocalException("User[${user.userId}]"))
            }
        }
    }

    override suspend fun getAllUsers(): Either<List<User>> {
        return withContext(Dispatchers.IO) {
            val users = userDao.getAll().map { it.toDomain() }
            Either.value(users)
        }
    }
}
