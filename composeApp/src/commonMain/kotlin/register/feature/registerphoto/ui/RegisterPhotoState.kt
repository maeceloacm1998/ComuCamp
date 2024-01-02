package register.feature.registerphoto.ui

import androidx.compose.ui.graphics.ImageBitmap
import core.utils.ErrorMessage

sealed interface RegisterPhotoUiState {
    val errorMessages: ErrorMessage?

    data class Data(
        val userName: String,
        val photoUrl: ImageBitmap?,
        override val errorMessages: ErrorMessage?,
    ) : RegisterPhotoUiState
}

data class RegisterPhotoState(
    val userName: String = "",
    val photoUrl: ImageBitmap? = null ,
    val errorMessages: ErrorMessage? = null,
) {
    fun toUiState(): RegisterPhotoUiState =
        RegisterPhotoUiState.Data(
            userName = userName,
            photoUrl = photoUrl,
            errorMessages = errorMessages,
        )
}