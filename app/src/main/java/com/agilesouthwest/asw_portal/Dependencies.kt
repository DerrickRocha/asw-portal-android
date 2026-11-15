package com.agilesouthwest.asw_portal

import com.agilesouthwest.asw_portal.data.AuthenticationRepository
import com.agilesouthwest.asw_portal.data.AuthenticationRepositoryImplementation
import okhttp3.OkHttpClient
import okhttp3.Request

object Dependencies {

    private val aswClient = OkHttpClient()

    val authenticationRepository: AuthenticationRepository = AuthenticationRepositoryImplementation(aswClient)

}
