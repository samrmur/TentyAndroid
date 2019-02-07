package com.tenty.tentyandroid.spamlist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.tenty.tentyandroid.db.entity.Spam
import com.tenty.tentyandroid.db.source.SpamDataSource

class SpamListViewModel constructor(
    private val spamDataSource: SpamDataSource
): ViewModel() {
    fun getSpamList(): LiveData<List<Spam>> = spamDataSource.getAllSpam()
}