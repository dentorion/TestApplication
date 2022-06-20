package com.entin.data.di

import com.entin.data.local.LocalDataSource
import com.entin.data.local.LocalDataSourceImpl
import com.entin.data.remote.RemoteDataSource
import com.entin.data.remote.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Dependency Injection for [RemoteDataSource]
 */

@Module
@InstallIn(SingletonComponent::class)
interface DataSourcesModule {

    @Singleton
    @Binds
    fun bindRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource

    @Singleton
    @Binds
    fun bindLocalDataSource(localDataSourceImpl: LocalDataSourceImpl): LocalDataSource
}