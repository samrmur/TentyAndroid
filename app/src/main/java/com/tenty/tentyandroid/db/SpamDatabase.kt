package com.tenty.tentyandroid.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tenty.tentyandroid.db.dao.SpamDao
import com.tenty.tentyandroid.db.entity.Spam

@Database(entities = [Spam::class], version = 1)
abstract class SpamDatabase : RoomDatabase() {
    abstract fun spamDao(): SpamDao
}