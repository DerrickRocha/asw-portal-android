package com.agilesouthwest.asw_portal.ui.composables.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.SecureTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.agilesouthwest.asw_portal.ui.theme.AswPortalTheme
import com.agilesouthwest.asw_portal.ui.theme.Typography

@Composable
fun SignupScreen() {
    SignupScreenContent()
}

@Composable
fun SignupScreenContent(modifier: Modifier = Modifier) {
    val emailFocusRequester = remember { FocusRequester() }
    LaunchedEffect(emailFocusRequester) {
        emailFocusRequester.requestFocus()
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Sign up", style = Typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            modifier = Modifier.fillMaxWidth().focusRequester(emailFocusRequester),
            state = TextFieldState(),
            placeholder = {Text(text = "Email")},
            lineLimits = TextFieldLineLimits.SingleLine,
            keyboardOptions = KeyboardOptions(showKeyboardOnFocus = true, imeAction = ImeAction.Next),
        )
        Spacer(modifier = Modifier.height(16.dp))
        SecureTextField(
            modifier = Modifier.fillMaxWidth(),
            state = TextFieldState(),
            placeholder = {Text(text = "Password")}
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            modifier = Modifier.fillMaxWidth(),
            state = TextFieldState(),
            placeholder = {Text(text = "Subdomain")},
            lineLimits = TextFieldLineLimits.SingleLine
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            modifier = Modifier.fillMaxWidth(),
            state = TextFieldState(),
            placeholder = { Text(text = "Company Name")},
            lineLimits = TextFieldLineLimits.SingleLine,
            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {}
        ) {
            Text(text = "Register")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignupScreenPreview() {
    AswPortalTheme() {
        SignupScreenContent()
    }
}