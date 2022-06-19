package com.entin.data.api

import retrofit2.http.GET

interface ApiTwoService {

    @GET("")
    suspend fun getApiDataOne()
}