package register.feature.registerphoto.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.darkrockstudios.libraries.mpfilepicker.FilePicker
import dev.icerock.moko.permissions.compose.BindEffect
import dev.icerock.moko.permissions.compose.PermissionsControllerFactory
import dev.icerock.moko.permissions.compose.rememberPermissionsControllerFactory
import register.feature.registerphoto.ui.model.User

internal data class RegisterPhotoRoute(
    val userName: String
) : Screen {
    @Composable
    override fun Content() {
        val permissionfactory: PermissionsControllerFactory = rememberPermissionsControllerFactory()
//        val mediafactory = rememberMediaPickerControllerFactory()

        val navigation = LocalNavigator.currentOrThrow
        val screenModel = getScreenModel<RegisterPhotoScreenModel>()
        val uiState by screenModel.uiState.collectAsState()
        val showFilePicker = screenModel.showFilePicker.collectAsState()

        LaunchedEffect(Unit) {
            screenModel.init(
                userName = userName,
                permissionController = permissionfactory.createPermissionsController()
            )
        }

        val fileType = listOf("jpg", "png")
        FilePicker(show = showFilePicker.value, fileExtensions = fileType) { file ->
            screenModel.closeButton()
            // do something with the file
        }

        RegisterPhotoRoute(
            uiState = uiState,
            onFinish = {},
            onOpenPhoto = { screenModel.openButton() },
            onBack = { navigation.pop() }
        )

        if (!screenModel.isAndroid()) {
            screenModel.permissionController?.let { BindEffect(it) }
        }
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