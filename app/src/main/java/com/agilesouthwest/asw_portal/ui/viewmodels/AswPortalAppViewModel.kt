package com.agilesouthwest.asw_portal.ui.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.agilesouthwest.asw_portal.ui.navigation.SignupKey

class AswPortalAppViewModel: ViewModel() {
    private val _backstack = mutableStateListOf<Any>(SignupKey)
    val backstack: List<Any> = _backstack

    fun add(any: Any) {
        _backstack.add(any)
    }

    fun back() {
        _backstack.removeLastOrNull()
    }
}