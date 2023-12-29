package register.feature.registerphoto.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.darkrockstudios.libraries.mpfilepicker.FilePicker
import register.feature.registerphoto.ui.model.User

internal data class RegisterPhotoRoute(
    val userName: String
) : Screen {
    @Composable
    override fun Content() {
        var showFilePicker by remember { mutableStateOf(false) }
        val navigation = LocalNavigator.currentOrThrow
        val screenModel = getScreenModel<RegisterPhotoScreenModel>()
        val uiState by screenModel.uiState.collectAsState()

        LaunchedEffect(Unit) {
            screenModel.init(userName)
        }

        val fileType = listOf("jpg", "png")
        FilePicker(show = showFilePicker, fileExtensions = fileType) { file ->
            showFilePicker = false
            // do something with the file
        }

        RegisterPhotoRoute(
            uiState = uiState,
            onFinish = {},
            onOpenPhoto = {showFilePicker = true},
            onBack = {
                navigation.pop()
            }
        )
    }
}

@Composable
fun RegisterPhotoRoute(
    uiState: RegisterPhotoUiState,
    onFinish: (user: User) -> Unit,
    onOpenPhoto: () -> Unit,
    onBack: () -> Unit,
) {
    check((uiState is RegisterPhotoUiState.Data))
    RegisterPhotoScreen(
        uiState = uiState,
        onFinish = onFinish,
        onOpenPhoto = onOpenPhoto,
        onBack = onBack
    )
}