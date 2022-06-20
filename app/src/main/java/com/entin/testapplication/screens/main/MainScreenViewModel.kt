package com.entin.testapplication.screens.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.entin.domain.repository.Repository
import com.entin.testapplication.di.DispatcherModule
import com.entin.testapplication.utils.Pending
import com.entin.testapplication.utils.Success
import com.entin.testapplication.utils.ViewResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

/**
 * MainScreen ViewModel for [MainScreenFragment]
 * Gets data from [Repository] and prepare MainScreen ViewState to show.
 */

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val repository: Repository,
    @Named(DispatcherModule.DISPATCHER_IO) private val dispatcherIO: CoroutineDispatcher
) : ViewModel() {

    private val _stateSplashScreen = MutableStateFlow(true)
    val stateSplashScreen = _stateSplashScreen.asStateFlow()

    private val _stateMainScreen = MutableLiveData<ViewResult<MainScreenViewState>>()
    val stateMainScreen: LiveData<ViewResult<MainScreenViewState>> = _stateMainScreen

    var isSuccessDownload = false

    fun downloadUsers() = viewModelScope.launch(dispatcherIO) {
        repository.downloadDataFromAllApi().collect { result ->
            result.onSuccess {
                isSuccessDownload = true
            }
            _stateSplashScreen.value = false
        }
    }

    fun getSavedUsers() = viewModelScope.launch(dispatcherIO) {
        _stateMainScreen.postValue(Pending())

        repository.getLocalSavedData().collect {
            _stateMainScreen.postValue(Success(data = MainScreenViewState(usersList = it.shuffled())))
        }
    }
}