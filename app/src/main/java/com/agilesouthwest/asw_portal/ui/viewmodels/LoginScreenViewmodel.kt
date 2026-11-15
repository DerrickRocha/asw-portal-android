package com.agilesouthwest.asw_portal.ui.viewmodels

import android.accounts.AuthenticatorException
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agilesouthwest.asw_portal.authenticationRepository
import com.agilesouthwest.asw_portal.data.AuthException
import com.agilesouthwest.asw_portal.data.AuthenticationRepository
import com.agilesouthwest.asw_portal.data.LoginResult
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
            snapshotFlow { emailState.text.toString() }
                .drop(1)
                .collect { _state.update { it.copy(emailError = null, formError = null) } }
        }
        viewModelScope.launch {
            snapshotFlow { passwordState.text.toString() }
                .drop(1)
                .collect { _state.update { it.copy(passwordError = null, formError = null) } }
        }
    }

    fun onLogin() {
        if (_state.value.isLoading) return
        val email = emailState.text.toString().trim()
        val password = passwordState.text.toString()

        val emailError = when {
            email.isEmpty() -> EmailError.Empty
            !email.contains('@') -> EmailError.Invalid
            else -> null
        }

        val passwordError = when {
            password.isEmpty() -> PasswordError.Empty
            else -> null
        }

        if (emailError != null || passwordError != null) {
            _state.update { it.copy(emailError = emailError, passwordError = passwordError) }
            return
        }

        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, formError = null) }
            try {
                when (val result = repository.login(email, password)) {
                    LoginResult.Success -> Unit
                    is LoginResult.Failure -> _state.update { it.copy(formError = result.error) }
                }
            } finally {
                _state.update { it.copy(isLoading = false) }
            }
        }
    }
}

enum class EmailError { Empty, Invalid }
enum class PasswordError { Empty }
enum class LoginError { InvalidCredentials, Network, Unknown }

private fun Throwable.toLoginError() = when (this) {
    is AuthException.Network -> LoginError.Network
    is AuthException.InvalidCredentials -> LoginError.InvalidCredentials
    else -> LoginError.Unknown
}

data class LoginScreenState(
    val isLoading: Boolean = false,
    val emailError: EmailError? = null,
    val passwordError: PasswordError? = null,
    val formError: LoginError? = null
)