package com.agilesouthwest.asw_portal.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.agilesouthwest.asw_portal.ui.auth.SignupScreen

@Composable
fun AswPortalApp(modifier: Modifier = Modifier) {
    val backstack = remember{ mutableStateListOf<Any>() }
    backstack.add(SignupKey)

    NavDisplay(
        modifier = modifier.fillMaxSize(),
        backStack = backstack,
        onBack = { backstack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry(SignupKey) {
                SignupScreen(modifier = Modifier)
            }
        }
    )
}