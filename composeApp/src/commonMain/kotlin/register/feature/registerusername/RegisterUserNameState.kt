package register.feature.registerusername

import core.utils.ErrorMessage


sealed interface RegisterUserNameUiState {
    val errorMessages: ErrorMessage?

    data class Data(
        val userName: String,
        override val errorMessages: ErrorMessage?,
    ) : RegisterUserNameUiState
}

data class RegisterUserNameState(
    val userName: String = "",
    val errorMessages: ErrorMessage? = null,
) {
    fun toUiState(): RegisterUserNameUiState =
        RegisterUserNameUiState.Data(
            userName = userName,
            errorMessages = errorMessages,
        )
}