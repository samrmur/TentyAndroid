package com.tenty.tentyandroid.core.module

import android.content.Context
import androidx.room.Room
import com.tenty.tentyandroid.R
import com.tenty.tentyandroid.db.SpamDatabase
import com.tenty.tentyandroid.db.dao.SpamDao
import com.tenty.tentyandroid.db.source.SpamDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun providesDatabase(applicationContext: Context): SpamDatabase =
        Room.databaseBuilder(
            applicationContext,
            SpamDatabase::class.java,
            applicationContext.getString(R.string.database_name)
        ).build()

    @Provides
    @Singleton
    fun providesSpamDao(database: SpamDatabase): SpamDao = database.spamDao()

    @Provides
    @Singleton
    fun providesSpamDataSource(spamDao: SpamDao) = SpamDataSource(spamDao)
}