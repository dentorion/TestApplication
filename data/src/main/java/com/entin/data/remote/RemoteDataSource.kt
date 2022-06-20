package com.entin.data.remote

import com.entin.data.model.apiOne.ApiOneResponse
import com.entin.data.model.apiTwo.ApiTwoResponse

interface RemoteDataSource {

    suspend fun downloadDataApiOne(): Result<ApiOneResponse>

    suspend fun downloadDataApiTwo(): Result<List<ApiTwoResponse>>
}