package com.entin.data.api

import com.entin.data.model.apiOne.ApiOneResponse
import retrofit2.http.GET

/**
 * Dailymotion
 */

interface ApiOneService {

    @GET("users")
    suspend fun getApiDataOne(): ApiOneResponse
}