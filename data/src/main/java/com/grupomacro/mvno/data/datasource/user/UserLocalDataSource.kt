package com.grupomacro.mvno.data.datasource.user

import com.grupomacro.mvno.domain.core.Either
import com.grupomacro.mvno.domain.user.model.User

interface UserLocalDataSource {
    suspend fun getUser(userId: String): Either<User>
    suspend fun addOrUpdateUser(user: User): Either<User>
    suspend fun getAllUsers(): Either<List<User>>
}
