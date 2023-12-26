package register.feature.registerphoto.ui

import core.utils.ErrorMessage

sealed interface RegisterPhotoUiState {
    val errorMessages: ErrorMessage?

    data class Data(
        val userName: String,
        val photoUrl: String,
        override val errorMessages: ErrorMessage?,
    ) : RegisterPhotoUiState
}

data class RegisterPhotoState(
    val userName: String = "",
    val photoUrl: String = "",
    val errorMessages: ErrorMessage? = null,
) {
    fun toUiState(): RegisterPhotoUiState =
        RegisterPhotoUiState.Data(
            userName = userName,
            photoUrl = photoUrl,
            errorMessages = errorMessages,
        )
}