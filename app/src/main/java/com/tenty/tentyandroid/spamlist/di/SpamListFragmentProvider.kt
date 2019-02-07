package com.tenty.tentyandroid.spamlist.di

import com.tenty.tentyandroid.core.scope.FragmentScope
import com.tenty.tentyandroid.spamlist.ui.SpamListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class SpamListFragmentProvider {
    @FragmentScope
    @ContributesAndroidInjector
    abstract fun providesSpamListFragment(): SpamListFragment
}