package com.agilesouthwest.asw_portal.ui.viewmodels

import android.util.Log
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agilesouthwest.asw_portal.authenticationRepository
import com.agilesouthwest.asw_portal.data.AuthenticationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginScreenViewmodel(
    private val repository: AuthenticationRepository = authenticationRepository
) : ViewModel() {

    val emailState = TextFieldState()
    val passwordState = TextFieldState()

    private val _state = MutableStateFlow(LoginScreenState())
    val state: StateFlow<LoginScreenState> = _state.asStateFlow()

    init {
        // Clear the error as soon as the user edits either field
        viewModelScope.launch {
            snapshotFlow { emailState.text.toString() to passwordState.text.toString() }
                .drop(1)
                .collect { _state.update { it.copy(errorMessage = null) } }
        }
    }

    fun onLogin() {

        if (_state.value.isLoading) return
        val email = emailState.text.toString().trim()
        val password = passwordState.text.toString().trim()

        viewModelScope.launch {

            _state.update { it.copy(isLoading = true, errorMessage = null) }
            repository.login(email, password)
                .onFailure {
                    _state.update { s -> s.copy(errorMessage = "Login failed. Check your email and password.") }
                }
            _state.update { it.copy(isLoading = false) }
        }
    }
}

data class LoginScreenState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)