package com.agilesouthwest.asw_portal.ui.composables.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.KeyboardActionHandler
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.SecureTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.agilesouthwest.asw_portal.ui.theme.AswPortalTheme

@Composable
fun LoginScreen(){
    LoginScreenContent()
}

@Composable
fun LoginScreenContent(
    onLoginClicked: () -> Unit = {},
    onSignupClicked: () -> Unit = {},
    onForgotPasswordClicked: () -> Unit = {}
) {

    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(focusRequester) {
        focusRequester.requestFocus()
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Agile Southwest CMS")
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            modifier = Modifier.focusRequester(focusRequester),
            state = rememberTextFieldState(),
            placeholder = { Text(text = "Email") },
            keyboardOptions = KeyboardOptions(
                showKeyboardOnFocus = true,
                imeAction = ImeAction.Next
            ),
        )
        Spacer(modifier = Modifier.height(16.dp))
        SecureTextField(
            state = rememberTextFieldState(),
            placeholder = { Text("Password") },
            keyboardOptions = KeyboardOptions(
                showKeyboardOnFocus = true,
                imeAction = ImeAction.Done
            ),
            onKeyboardAction = KeyboardActionHandler {
                onLoginClicked()
                keyboardController?.hide()
            }
        )
        Spacer(modifier = Modifier.height(22.dp))
        Button(onClick = {
            onLoginClicked()
            keyboardController?.hide()
        }) {
            Text(text = "Login")
        }
        Spacer(modifier = Modifier.height(16.dp))
        TextButton(onClick = {
            onSignupClicked()
            keyboardController?.hide()
        }) {
            Text(text = "Sign Up")
        }
        TextButton(onClick = {
            onForgotPasswordClicked()
            keyboardController?.hide()
        }) {
            Text(text = "Forgot Password")
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    AswPortalTheme() {
        LoginScreenContent()
    }
}