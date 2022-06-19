package com.entin.data.remote

interface RemoteDataSource {

    suspend fun downloadDataApiOne(): Result<Any>

    suspend fun downloadDataApiTwo(): Result<Any>
}