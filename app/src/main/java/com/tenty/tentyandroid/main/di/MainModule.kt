package com.tenty.tentyandroid.main.di

import com.tenty.tentyandroid.core.scope.ActivityScope
import com.tenty.tentyandroid.main.presentation.MainNavigator
import com.tenty.tentyandroid.main.ui.MainActivity
import dagger.Module
import dagger.Provides

@Module
class MainModule {
    @ActivityScope
    @Provides
    fun providesMainNavigator(activity: MainActivity): MainNavigator = MainNavigator(activity)
}