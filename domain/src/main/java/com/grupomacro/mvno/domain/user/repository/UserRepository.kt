package com.grupomacro.mvno.domain.user.repository

import com.grupomacro.mvno.domain.user.model.User

interface UserRepository {

    suspend fun getUser(userId: String): User

    suspend fun saveUser(user: User): User
}
