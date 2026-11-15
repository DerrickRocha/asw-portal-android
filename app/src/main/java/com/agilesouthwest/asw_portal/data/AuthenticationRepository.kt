package com.agilesouthwest.asw_portal.data

import com.agilesouthwest.asw_portal.ui.viewmodels.LoginError
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface AuthenticationRepository {
    suspend fun isLoggedIn(): Boolean
    suspend fun login(username: String, password: String): LoginResult
}

class AuthenticationRepositoryImplementation(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) :
    AuthenticationRepository {
    override suspend fun isLoggedIn(): Boolean = withContext(Dispatchers.IO) {
        false
    }

    override suspend fun login(username: String, password: String): LoginResult =
        withContext(dispatcher) {
            try {
                LoginResult.Success
            } catch (exception: CancellationException) {
                throw exception
            }
        }
}

sealed interface LoginResult {
    data object Success : LoginResult
    data class Failure(val error: LoginError) : LoginResult
}
sealed class AuthException(message: String? = null, cause: Throwable? = null) :
    Exception(message, cause) {
    class InvalidCredentials : AuthException()
    class Network(cause: Throwable? = null) : AuthException(cause = cause)
    class Unknown(cause: Throwable? = null) : AuthException(cause = cause)
}

