package com.tenty.tentyandroid.core.module

import com.tenty.tentyandroid.receiver.PhoneReceiver
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ReceiverBuilder {
    @ContributesAndroidInjector
    abstract fun providesPhoneReceiver(): PhoneReceiver
}