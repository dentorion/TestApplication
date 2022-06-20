package com.entin.data.api

import com.entin.data.model.apiTwo.ApiTwoResponse
import retrofit2.http.GET

/**
 * Git
 */

interface ApiTwoService {

    @GET("users")
    suspend fun getApiDataOne(): List<ApiTwoResponse>
}