package com.agilesouthwest.asw_portal.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface AuthenticationRepository {
    suspend fun isLoggedIn(): Boolean
    suspend fun login(username: String, password: String): Result<Unit>
}

class AuthenticationRepositoryImplementation(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) :
    AuthenticationRepository {
    override suspend fun isLoggedIn(): Boolean = withContext(Dispatchers.IO) {
        false
    }

    override suspend fun login(username: String, password: String): Result<Unit> =
        withContext(dispatcher) {
            runCatching {
            }
        }
}