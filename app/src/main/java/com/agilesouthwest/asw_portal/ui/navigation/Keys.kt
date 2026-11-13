package com.agilesouthwest.asw_portal.ui.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface AppKey : NavKey

@Serializable
data object LoginKey: AppKey

@Serializable
data object SignupKey: AppKey

@Serializable
data object HomeKey: AppKey

@Serializable
data object SplashKey: AppKey
