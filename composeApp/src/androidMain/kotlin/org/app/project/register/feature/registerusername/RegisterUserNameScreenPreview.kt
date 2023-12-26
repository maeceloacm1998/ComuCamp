package org.app.project.register.feature.registerusername

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import register.feature.registerusername.RegisterUserNameRoute
import register.feature.registerusername.RegisterUserNameUiState

@Preview
@Composable
fun RegisterUserNameScreenPreview() {
    RegisterUserNameRoute(
        uiState = RegisterUserNameUiState.Data(
            userName = "Marcelo Antônio",
            errorMessages = null
        ),
        onNext = {},
        onUserNameListener = {},
        onBack = {}
    )
}