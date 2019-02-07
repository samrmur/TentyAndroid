package com.tenty.tentyandroid.audio.di

import com.tenty.tentyandroid.audio.ui.AudioFragment
import com.tenty.tentyandroid.core.scope.FragmentScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AudioFragmentProvider {
    @FragmentScope
    @ContributesAndroidInjector
    abstract fun providesAudioFragment(): AudioFragment
}