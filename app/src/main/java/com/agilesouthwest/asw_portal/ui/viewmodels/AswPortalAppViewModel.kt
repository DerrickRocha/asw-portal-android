package com.agilesouthwest.asw_portal.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agilesouthwest.asw_portal.authenticationRepository
import com.agilesouthwest.asw_portal.data.AuthenticationRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class AswPortalAppViewModel(private val repository: AuthenticationRepository = authenticationRepository) :
    ViewModel() {
    private val _state = MutableStateFlow<AswPortalState>(AswPortalState.Splash)
    val state: StateFlow<AswPortalState> = _state.asStateFlow()

    init {
        // running the check concurrently and enforcing a minimum splash duration
        viewModelScope.launch {
            val minSplash = async { delay(2.seconds) }
            val isLoggedIn = repository.isLoggedIn()
            minSplash.await()
            _state.value =
                if (isLoggedIn) AswPortalState.Home else AswPortalState.Authenticate
        }
    }
}

sealed interface AswPortalState {
    data object Splash : AswPortalState
    data object Home : AswPortalState
    data object Authenticate : AswPortalState
}