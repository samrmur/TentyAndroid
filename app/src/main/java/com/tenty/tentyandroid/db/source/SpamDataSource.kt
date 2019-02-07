package com.tenty.tentyandroid.db.source

import androidx.lifecycle.LiveData
import com.tenty.tentyandroid.db.dao.SpamDao
import com.tenty.tentyandroid.db.entity.Spam

class SpamDataSource constructor(
    private val spamDao: SpamDao
): SpamRepository {
    override fun getAllSpam(): LiveData<List<Spam>> = spamDao.getAll()

    override fun getSpamByPhoneNumber(phoneNumber: String): LiveData<List<Spam>> = spamDao.getByPhoneNumber(phoneNumber)

    override fun insertSpam(spam: Spam) = spamDao.insertSpam(spam)
}