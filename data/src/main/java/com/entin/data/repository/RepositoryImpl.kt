package com.entin.data.repository

import com.entin.data.local.LocalDataSource
import com.entin.data.remote.RemoteDataSource
import com.entin.data.utils.mapToDomainModel
import com.entin.data.utils.mapToRoomModel
import com.entin.domain.model.UserDomain
import com.entin.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : Repository {

    override fun downloadDataFromAllApi(): Flow<Result<Boolean>> = flow {
        var firstResult = false
        var secondResult = false

        remoteDataSource.downloadDataApiOne().onSuccess { responseOne ->
            val usersFirstList = responseOne.map { apiOneResponse ->
                apiOneResponse.mapToRoomModel()
            }
            localDataSource.saveAllUsers(usersFirstList)
            firstResult = true
        }

        remoteDataSource.downloadDataApiTwo().onSuccess { responseTwo ->
            val usersSecondList = responseTwo.map { apiTwoResponse ->
                apiTwoResponse.mapToRoomModel()
            }
            localDataSource.saveAllUsers(usersSecondList)
            secondResult = true
        }

        if (firstResult || secondResult) {
            emit(Result.success(true))
        } else {
            emit(Result.failure(IOException()))
        }
    }

    override fun getLocalSavedData(): Flow<List<UserDomain>> = flow {
        localDataSource.getAllUsers().collect { userRoomList ->
            val result = userRoomList.map {
                it.mapToDomainModel()
            }
            emit(result)
        }
    }
}