package com.entin.domain.repository

import com.entin.domain.model.UserDomain
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface
 * Fetches data from RemoteDataSource
 */

interface Repository {

    fun downloadDataFromAllApi(): Flow<Result<Boolean>>

    fun getLocalSavedData(): Flow<List<UserDomain>>
}