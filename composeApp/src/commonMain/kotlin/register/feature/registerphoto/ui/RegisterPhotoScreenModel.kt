package register.feature.registerphoto.ui

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class RegisterPhotoScreenModel : ScreenModel {
    private val viewModelState = MutableStateFlow(RegisterPhotoState())

    val uiState = viewModelState
        .map(RegisterPhotoState::toUiState)
        .stateIn(
            screenModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )

    fun init(userName: String) {
        viewModelState.update { it.copy(userName = userName) }
    }
}