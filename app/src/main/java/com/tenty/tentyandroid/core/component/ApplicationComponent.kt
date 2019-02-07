package com.tenty.tentyandroid.core.component

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import com.tenty.tentyandroid.core.TentyApplication
import com.tenty.tentyandroid.core.module.*
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ApplicationModule::class,
    ActivityBuilder::class,
    DatabaseModule::class,
    NetworkModule::class,
    ReceiverBuilder::class
])
interface ApplicationComponent: AndroidInjector<TentyApplication> {
    @Component.Builder
    abstract class Builder: AndroidInjector.Builder<TentyApplication>()
}