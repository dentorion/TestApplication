package com.entin.testapplication.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DispatcherModule {

    @Singleton
    @Provides
    @Named(DISPATCHER_IO)
    fun providesDispatcherIO(): CoroutineDispatcher =
        Dispatchers.IO

    const val DISPATCHER_IO = "DISPATCHER_IO"
}