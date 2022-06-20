package com.entin.data.di

import com.entin.data.api.ApiTwoService
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton


/**
 * Dependency Injection for Api 2
 * Git
 */

@Module
@InstallIn(SingletonComponent::class)
object ApiTwoModule {

    @Singleton
    @Provides
    @Named(NAME_API)
    fun provideRetrofit(gson: Gson, client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(SERVER_API)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    @Singleton
    @Provides
    fun provideApiServer(@Named(NAME_API) retrofit: Retrofit): ApiTwoService =
        retrofit.create(ApiTwoService::class.java)

    private const val NAME_API = "api2"
    private const val SERVER_API = "https://api.github.com/"
}