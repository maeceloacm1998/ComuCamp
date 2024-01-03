package register.feature.registerphoto.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import dev.icerock.moko.media.compose.BindMediaPickerEffect
import dev.icerock.moko.media.compose.rememberMediaPickerControllerFactory
import dev.icerock.moko.permissions.PermissionsController
import dev.icerock.moko.permissions.compose.BindEffect
import dev.icerock.moko.permissions.compose.PermissionsControllerFactory
import dev.icerock.moko.permissions.compose.rememberPermissionsControllerFactory
import register.feature.registerphoto.ui.model.User

internal data class RegisterPhotoRoute(
    val userName: String
) : Screen {
    @Composable
    override fun Content() {
        val navigation = LocalNavigator.currentOrThrow
        val coroutineScope = rememberCoroutineScope()

        val mediaFactory = rememberMediaPickerControllerFactory()
        val mediaPicker = remember(mediaFactory) { mediaFactory.createMediaPickerController() }

        val permissionFactory: PermissionsControllerFactory = rememberPermissionsControllerFactory()
        val permissionController: PermissionsController = remember(permissionFactory) {
            permissionFactory.createPermissionsController()
        }

        val screenModel = getScreenModel<RegisterPhotoScreenModel>()
        val uiState by screenModel.uiState.collectAsState()

        BindEffect(permissionController)
        BindMediaPickerEffect(mediaPicker)

        DisposableEffect(Unit) {
            screenModel.init(userName = userName)
            onDispose {}
        }

        RegisterPhotoRoute(
            uiState = uiState,
            onFinish = {},
            onOpenPhoto = {
                screenModel.openPhoto(
                    coroutineScope = coroutineScope,
                    mediaPickerController = mediaPicker,
                    permissionsController = permissionController
                )
            },
            onBack = { navigation.pop() }
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