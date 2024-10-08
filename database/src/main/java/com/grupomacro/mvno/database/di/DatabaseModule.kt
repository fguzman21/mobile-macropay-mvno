package com.grupomacro.mvno.database.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.grupomacro.mvno.database.AppDatabase
import com.grupomacro.mvno.database.dao.SessionDao
import com.grupomacro.mvno.database.dao.UserDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun getSessionDao(appDatabase: AppDatabase): SessionDao {
        return appDatabase.getSessionDao()
    }

    @Singleton
    @Provides
    fun getUserDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.getUserDao()
    }



    @Singleton
    @Provides
    fun getDatabase(context: Context): AppDatabase {
        return getDatabaseBuilder(context, true).build()
    }

    private fun getDatabaseBuilder(context: Context, isInMemory: Boolean = false): RoomDatabase.Builder<AppDatabase> {
        return if (isInMemory) {
            Room.inMemoryDatabaseBuilder(
                context,
                AppDatabase::class.java,
            )
        } else {
            Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                DATABASE_NAME
            )
        }
    }

    companion object {
        private const val DATABASE_NAME = "grupomacro_mvno_database"
    }
}
