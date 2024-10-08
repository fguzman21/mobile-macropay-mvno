package com.grupomacro.mvno.database.di

import android.content.Context
import com.grupomacro.mvno.database.DatabaseEntryPoint
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DatabaseModule::class
    ]
)
interface MainDatabaseComponent {

    fun inject(databaseEntryPoint: DatabaseEntryPoint)

    @Component.Builder
    interface Builder {

        fun build(): MainDatabaseComponent

        @BindsInstance
        fun context(context: Context): Builder
    }
}
