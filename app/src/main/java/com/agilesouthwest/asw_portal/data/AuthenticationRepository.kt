package com.agilesouthwest.asw_portal.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface AuthenticationRepository {
    suspend fun isLoggedIn(): Boolean
}

class AuthenticationRepositoryImplementation(): AuthenticationRepository {
    override suspend fun isLoggedIn(): Boolean = withContext(Dispatchers.IO) {
        false
    }
}