package com.agilesouthwest.asw_portal.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.agilesouthwest.asw_portal.ui.auth.SignupScreen
import com.agilesouthwest.asw_portal.ui.navigation.SignupKey
import com.agilesouthwest.asw_portal.ui.viewmodels.AswPortalAppViewModel

@Composable
fun AswPortalApp(
    modifier: Modifier = Modifier,
    viewModel: AswPortalAppViewModel = viewModel()
) {
    val backstack = viewModel.backstack

    NavDisplay(
        modifier = modifier.fillMaxSize(),
        backStack = backstack,
        onBack = { viewModel.back() },
        entryProvider = entryProvider {
            entry(SignupKey) {
                SignupScreen(modifier = Modifier)
            }
        }
    )
}