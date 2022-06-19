package com.entin.data.remote

import com.entin.data.api.ApiOneService
import com.entin.data.api.ApiTwoService
import com.entin.data.utils.safeApiRequest
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val apiOneService: ApiOneService,
    private val apiTwoService: ApiTwoService,
) : RemoteDataSource {

    override suspend fun downloadDataApiOne(): Result<Any> =
        safeApiRequest {
            apiOneService.getApiDataOne()
        }

    override suspend fun downloadDataApiTwo(): Result<Any> =
        safeApiRequest {
            apiTwoService.getApiDataOne()
        }
}