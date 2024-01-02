package register.feature.registerphoto.ui

import ImageLoader
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import dev.icerock.moko.media.compose.toImageBitmap
import dev.icerock.moko.media.picker.MediaPickerController
import dev.icerock.moko.media.picker.MediaSource
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

    private var mediaPickerController: MediaPickerController? = null
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
        mediaPickerController: MediaPickerController
    ) {
        viewModelState.update { it.copy(userName = userName) }
        this.permissionController = permissionController
        this.mediaPickerController = mediaPickerController
    }

    fun openButton() {
        if (!isAndroid()) {
            pickerImageWithIos()
        } else {
            _showFilePicker.value = true
        }
    }

    fun pickerImageWithAndroid(path: String) {
        screenModelScope.launch {
            val bitmap = ImageLoader().loadImageFromBytes(path)
            viewModelState.update { it.copy(photoUrl = bitmap) }
        }
    }

    private fun pickerImageWithIos() {
        screenModelScope.launch {
            permissionController?.providePermission(Permission.GALLERY)
            val result = mediaPickerController?.pickImage(MediaSource.GALLERY)
            viewModelState.update { it.copy(photoUrl = result?.toImageBitmap()) }
        }
    }


    fun closeButton() {
        _showFilePicker.value = false
    }

    fun isAndroid(): Boolean {
        return getPlatform().name.contains("Android")
    }
}