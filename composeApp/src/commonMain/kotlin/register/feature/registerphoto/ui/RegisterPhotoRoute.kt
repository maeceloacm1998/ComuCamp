package register.feature.registerphoto.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import register.feature.registerphoto.ui.model.User

internal data class RegisterPhotoRoute(
    val userName: String
) : Screen {
    @Composable
    override fun Content() {
        val navigation = LocalNavigator.currentOrThrow
        val screenModel = getScreenModel<RegisterPhotoScreenModel>()
        val uiState by screenModel.uiState.collectAsState()

        LaunchedEffect(Unit) {
            screenModel.init(userName)
        }

        RegisterPhotoRoute(
            uiState = uiState,
            onFinish = {},
            onOpenPhoto = {},
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