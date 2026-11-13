package com.agilesouthwest.asw_portal.ui.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.agilesouthwest.asw_portal.ui.composables.auth.LoginScreen
import com.agilesouthwest.asw_portal.ui.composables.auth.SignupScreen
import com.agilesouthwest.asw_portal.ui.navigation.HomeKey
import com.agilesouthwest.asw_portal.ui.navigation.LoginKey
import com.agilesouthwest.asw_portal.ui.navigation.SignupKey
import com.agilesouthwest.asw_portal.ui.navigation.SplashKey
import com.agilesouthwest.asw_portal.ui.viewmodels.AswPortalAppViewModel
import com.agilesouthwest.asw_portal.ui.viewmodels.AswPortalState

@Composable
fun AswPortalApp(
    modifier: Modifier = Modifier,
    viewModel: AswPortalAppViewModel = viewModel()
) {
    val state: AswPortalState by viewModel.state.collectAsState()
    val backstack = rememberNavBackStack(SplashKey)

    LaunchedEffect(state) {
        val key: NavKey = when (state) {
            AswPortalState.Authenticate -> {
                LoginKey
            }

            AswPortalState.Home -> {
                HomeKey
            }

            AswPortalState.Splash -> {
                SplashKey
            }
        }
        if (backstack.firstOrNull() != key) {
            backstack.clear()
            backstack.add(key)
        }
    }

    NavDisplay(
        modifier = modifier.fillMaxSize(),
        backStack = backstack,
        onBack = { backstack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry(SignupKey) {
                SignupScreen(modifier = Modifier)
            }
            entry(LoginKey) {
                LoginScreen()
            }
            entry(SplashKey) {
                SplashScreen()
            }
            entry(HomeKey) {
                HomeScreen()
            }
        }
    )
}