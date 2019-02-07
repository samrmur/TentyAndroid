package com.tenty.tentyandroid.permissions.di

import com.tenty.tentyandroid.core.scope.FragmentScope
import com.tenty.tentyandroid.permissions.ui.PermissionsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class PermissionsFragmentProvider {
    @FragmentScope
    @ContributesAndroidInjector
    abstract fun providesPermissionsFragment(): PermissionsFragment
}