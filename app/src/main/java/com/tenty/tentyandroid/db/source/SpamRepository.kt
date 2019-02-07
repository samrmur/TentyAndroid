package com.tenty.tentyandroid.db.source

import androidx.lifecycle.LiveData
import com.tenty.tentyandroid.db.entity.Spam

interface SpamRepository {
    fun getAllSpam(): LiveData<List<Spam>>
    fun getSpamByPhoneNumber(phoneNumber: String): LiveData<List<Spam>>
    fun insertSpam(spam: Spam)
}