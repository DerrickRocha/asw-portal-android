package com.agilesouthwest.asw_portal.ui.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.agilesouthwest.asw_portal.ui.composables.auth.LoginScreen
import com.agilesouthwest.asw_portal.ui.composables.auth.SignupScreen
import com.agilesouthwest.asw_portal.ui.navigation.AppKey
import com.agilesouthwest.asw_portal.ui.navigation.AuthKey
import com.agilesouthwest.asw_portal.ui.viewmodels.AswPortalAppViewModel
import com.agilesouthwest.asw_portal.ui.viewmodels.AswPortalState

@Composable
fun AswPortalApp(
    modifier: Modifier = Modifier,
    viewModel: AswPortalAppViewModel = viewModel()
) {
    val state: AswPortalState by viewModel.state.collectAsState()

    when(state) {
        AswPortalState.Authenticate -> AuthenticationNavDisplay(modifier)
        AswPortalState.Home -> HomeNavDisplay(modifier)
        AswPortalState.Splash -> SplashScreen(modifier)
    }
}

@Composable
fun HomeNavDisplay(modifier: Modifier) {
    val backstack = rememberNavBackStack(AppKey.HomeKey)
    NavDisplay(
        modifier = modifier.fillMaxSize(),
        backStack = backstack,
        onBack = { if (backstack.size > 1) backstack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry(AppKey.HomeKey) {
                HomeScreen()
            }
        }
    )
}

@Composable
fun AuthenticationNavDisplay(modifier: Modifier = Modifier) {
    val backstack = rememberNavBackStack(AuthKey.LoginKey)
    NavDisplay(
        modifier = modifier.fillMaxSize(),
        backStack = backstack,
        onBack = { if (backstack.size > 1) backstack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry(AuthKey.SignupKey) {
                SignupScreen()
            }
            entry(AuthKey.LoginKey) {
                LoginScreen(
                    onSignupClicked = {
                        backstack.add(AuthKey.SignupKey)
                    },
                    onForgotPasswordClicked = {
                        backstack.add(AuthKey.ResetPasswordKey)
                    },
                )
            }
            entry(AuthKey.ResetPasswordKey) {
                Text(text = "Reset password screen")
            }
        }
    )
}