package com.tenty.tentyandroid.core.module

import android.content.Context
import android.media.MediaPlayer
import dagger.Module
import dagger.Provides
import com.tenty.tentyandroid.core.TentyApplication
import javax.inject.Singleton

@Module
class ApplicationModule {
    @Singleton
    @Provides
    fun providesApplicationContext(application: TentyApplication): Context = application.applicationContext

    @Singleton
    @Provides
    fun providesMediaPlayer(): MediaPlayer = MediaPlayer()
}