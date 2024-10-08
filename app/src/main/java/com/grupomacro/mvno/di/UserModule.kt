package com.grupomacro.mvno.di

import com.grupomacro.mvno.data.datasource.user.UserLocalDataSource
import com.grupomacro.mvno.data.datasource.user.impl.UserLocalDataSourceImpl
import com.grupomacro.mvno.data.repository.UserRepositoryImpl
import com.grupomacro.mvno.domain.user.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

class UserModule {

    @Module
    @InstallIn(SingletonComponent::class)
    interface Bindings {

        @Singleton
        @Binds
        fun getUserRepository(impl: UserRepositoryImpl): UserRepository

        @Singleton
        @Binds
        fun getUserLocalDataSource(impl: UserLocalDataSourceImpl): UserLocalDataSource
    }
}
