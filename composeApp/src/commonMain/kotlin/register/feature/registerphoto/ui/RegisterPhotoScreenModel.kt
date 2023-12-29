package register.feature.registerphoto.ui

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import dev.icerock.moko.permissions.Permission
import dev.icerock.moko.permissions.PermissionsController
import getPlatform
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegisterPhotoScreenModel : ScreenModel {
    private val viewModelState = MutableStateFlow(RegisterPhotoState())
    private val _showFilePicker = MutableStateFlow(false)

    var permissionController: PermissionsController? = null

    val uiState = viewModelState
        .map(RegisterPhotoState::toUiState)
        .stateIn(
            screenModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )

    val showFilePicker: StateFlow<Boolean> get() = _showFilePicker

    fun init(
        userName: String,
        permissionController: PermissionsController,
//        mediaPickerController: MediaPickerController
    ) {
        viewModelState.update { it.copy(userName = userName) }
        this.permissionController = permissionController
//        this.mediaPickerController = mediaPickerController
    }

    fun openButton() {
        if (!isAndroid()) {
            screenModelScope.launch {
                permissionController?.providePermission(Permission.GALLERY)
            }
        } else {
            _showFilePicker.value = true
        }
    }

    fun closeButton() {
        _showFilePicker.value = false
    }

    fun isAndroid(): Boolean {
        return getPlatform().name.contains("Android")
    }
}