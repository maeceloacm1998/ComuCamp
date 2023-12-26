package register.feature.registerusername

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import register.feature.registerphoto.ui.RegisterPhotoRoute

internal class RegisterUserNameRoute : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val screenModel = getScreenModel<RegisterUserNameScreenModel>()
        val uiState by screenModel.uiState.collectAsState()

        RegisterUserNameRoute(
            uiState = uiState,
            onUserNameListener = { screenModel.onUpdateUserName(it) },
            onNext = { navigator.push(RegisterPhotoRoute(userName = it)) },
            onBack = { navigator.pop() }
        )
    }
}

@Composable
fun RegisterUserNameRoute(
    uiState: RegisterUserNameUiState,
    onUserNameListener: (userName: String) -> Unit,
    onNext: (userName: String) -> Unit,
    onBack: () -> Unit
) {
    check(uiState is RegisterUserNameUiState.Data)
    RegisterUserNameScreen(
        uiState = uiState,
        onUserNameListener = onUserNameListener,
        onNext = onNext,
        onBack = onBack
    )
}