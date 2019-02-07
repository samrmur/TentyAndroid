package com.tenty.tentyandroid.core.module

import com.tenty.tentyandroid.audio.di.AudioFragmentProvider
import com.tenty.tentyandroid.core.scope.ActivityScope
import com.tenty.tentyandroid.main.di.MainModule
import com.tenty.tentyandroid.main.ui.MainActivity
import com.tenty.tentyandroid.permissions.di.PermissionsFragmentProvider
import com.tenty.tentyandroid.spamlist.di.SpamListFragmentProvider
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {
    @ActivityScope
    @ContributesAndroidInjector(modules = [
        MainModule::class,
        PermissionsFragmentProvider::class,
        SpamListFragmentProvider::class,
        AudioFragmentProvider::class
    ])
    abstract fun bindsMainActivity(): MainActivity
}
