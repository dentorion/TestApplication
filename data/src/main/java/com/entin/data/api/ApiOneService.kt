package com.entin.data.api

import retrofit2.http.GET

interface ApiOneService {

    @GET("")
    suspend fun getApiDataOne()
}