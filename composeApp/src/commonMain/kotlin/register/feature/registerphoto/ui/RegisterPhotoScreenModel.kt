package register.feature.registerphoto.ui

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import dev.icerock.moko.media.compose.toImageBitmap
import dev.icerock.moko.media.picker.MediaPickerController
import dev.icerock.moko.media.picker.MediaSource
import dev.icerock.moko.permissions.DeniedAlwaysException
import dev.icerock.moko.permissions.DeniedException
import dev.icerock.moko.permissions.Permission
import dev.icerock.moko.permissions.PermissionsController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

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

    fun openPhoto(
        coroutineScope: CoroutineScope,
        mediaPickerController: MediaPickerController,
        permissionsController: PermissionsController
    ) {
        coroutineScope.launch {
            try {
                permissionsController.providePermission(Permission.GALLERY)
                val result = mediaPickerController.pickImage(MediaSource.GALLERY)
                viewModelState.update { it.copy(photoUrl = result.toImageBitmap()) }
            } catch (deniedAlways: DeniedAlwaysException) {
                // TODO SNACKBAR COM ACAO
            } catch (denied: DeniedException) {
                // TODO SNACKBAR COM ACAO
            }
        }
    }
}