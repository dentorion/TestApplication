package com.entin.data.remote

import com.entin.data.model.apiOne.ApiOneResponse
import com.entin.data.model.apiOne.ApiOneResponseItem
import com.entin.data.model.apiTwo.ApiTwoResponse

interface RemoteDataSource {

    suspend fun downloadDataApiOne(): Result<ArrayList<ApiOneResponseItem>>

    suspend fun downloadDataApiTwo(): Result<List<ApiTwoResponse>>
}