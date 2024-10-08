package com.grupomacro.mvno.di

import com.grupomacro.mvno.data.datasource.SessionLocalDataSource
import com.grupomacro.mvno.data.datasource.SessionRemoteDataSource
import com.grupomacro.mvno.data.datasource.session.impl.SessionLocalDataSourceImpl
import com.grupomacro.mvno.data.datasource.session.impl.SessionRemoteDataSourceImpl
import com.grupomacro.mvno.data.repository.SessionRepositoryImpl
import com.grupomacro.mvno.domain.session.repository.SessionRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

class LoginModule {

    @Module
    @InstallIn(SingletonComponent::class)
    interface Bindings {

        @Singleton
        @Binds
        fun getSessionRepository(impl: SessionRepositoryImpl): SessionRepository

       @Singleton
        @Binds
        fun getSessionRemoteDataSource(impl: SessionRemoteDataSourceImpl): SessionRemoteDataSource

        @Singleton
        @Binds
        fun getSessionLocalDataSource(impl: SessionLocalDataSourceImpl): SessionLocalDataSource
    }
}
