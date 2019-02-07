package com.tenty.tentyandroid.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tenty.tentyandroid.db.entity.Spam

@Dao
interface SpamDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSpam(vararg spam: Spam)

    @Query("SELECT * FROM calls")
    fun getAll(): LiveData<List<Spam>>

    @Query("SELECT * FROM calls WHERE phone_number=:phoneNumber")
    fun getByPhoneNumber(phoneNumber: String): LiveData<List<Spam>>
}