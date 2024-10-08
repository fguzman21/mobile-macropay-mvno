package com.grupomacro.mvno.data.repository

import android.util.Log
import com.grupomacro.mvno.data.datasource.user.UserLocalDataSource
import com.grupomacro.mvno.domain.user.model.User
import com.grupomacro.mvno.domain.user.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userLocalDataSource: UserLocalDataSource
) : UserRepository {

    override suspend fun getUser(userId: String): User {
        return userLocalDataSource.getUser(userId).getOrThrow().also {
            Log.d(TAG, "getUser($userId): $it")
        }
    }

    override suspend fun saveUser(user: User): User {
        Log.d(TAG, "saveUser: $user")
        return userLocalDataSource.addOrUpdateUser(user).getOrThrow()
    }

    companion object {
        private const val TAG = "UserRepositoryImpl"
    }
}
