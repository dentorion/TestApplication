package com.entin.testapplication.screens.main

import com.entin.domain.model.UserDomain

/**
 * ViewState for [MainScreenFragment]
 */

data class MainScreenViewState(
    val usersList: List<UserDomain>
)