package com.entin.data.remote

import com.entin.data.api.ApiOneService
import com.entin.data.api.ApiTwoService
import com.entin.data.model.apiOne.ApiOneResponse
import com.entin.data.model.apiOne.ApiOneResponseItem
import com.entin.data.model.apiTwo.ApiTwoResponse
import com.entin.data.utils.safeApiRequest
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val apiOneService: ApiOneService,
    private val apiTwoService: ApiTwoService,
) : RemoteDataSource {

    override suspend fun downloadDataApiOne(): Result<ArrayList<ApiOneResponseItem>> =
        safeApiRequest {
            apiOneService.getApiDataOne().usersList
        }

    override suspend fun downloadDataApiTwo(): Result<List<ApiTwoResponse>> =
        safeApiRequest {
            apiTwoService.getApiDataOne()
        }
}
