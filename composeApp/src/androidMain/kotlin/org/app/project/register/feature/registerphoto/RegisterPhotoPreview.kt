package org.app.project.register.feature.registerphoto

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import register.feature.registerphoto.ui.RegisterPhotoRoute
import register.feature.registerphoto.ui.RegisterPhotoUiState

@Preview
@Composable
fun RegisterPhotoPreview() {
    RegisterPhotoRoute(
        uiState = RegisterPhotoUiState.Data(
            userName = "",
            photoUrl = null,
            errorMessages = null
        ),
        onFinish = {},
        onOpenPhoto = {},
        onBack = {}
    )
}