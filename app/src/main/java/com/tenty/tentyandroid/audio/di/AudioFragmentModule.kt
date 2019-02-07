package com.tenty.tentyandroid.audio.di

import androidx.lifecycle.Lifecycle
import com.tenty.tentyandroid.audio.ui.AudioFragment
import com.tenty.tentyandroid.core.scope.FragmentScope
import com.tenty.tentyandroid.util.lifecycle.LifecycleJob
import dagger.Module
import dagger.Provides

@Module
class AudioFragmentModule {
    @FragmentScope
    @Provides
    fun providesLifecycle(fragment: AudioFragment): Lifecycle = fragment.lifecycle

    @FragmentScope
    @Provides
    fun providesJob(lifecycle: Lifecycle): LifecycleJob = LifecycleJob(lifecycle)
}