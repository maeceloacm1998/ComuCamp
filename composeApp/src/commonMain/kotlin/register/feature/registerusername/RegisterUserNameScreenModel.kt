package register.feature.registerusername

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class RegisterUserNameScreenModel : ScreenModel {
    private val viewModelState = MutableStateFlow(RegisterUserNameState())

    val uiState = viewModelState
        .map(RegisterUserNameState::toUiState)
        .stateIn(
            screenModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )

    fun onUpdateUserName(userName: String) {
        viewModelState.update { it.copy(userName = userName) }
    }
}