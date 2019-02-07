package com.tenty.tentyandroid.spamlist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tenty.tentyandroid.db.source.SpamDataSource
import javax.inject.Inject

class SpamListViewModelFactory @Inject constructor(
    private val dataSource: SpamDataSource
): ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) = SpamListViewModel(dataSource) as T
}