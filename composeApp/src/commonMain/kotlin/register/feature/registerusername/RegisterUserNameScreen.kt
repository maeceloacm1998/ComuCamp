package register.feature.registerusername

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import components.button.OutlinedButtonCustom
import components.textfield.TextFieldCustom
import theme.CustomDimensions
import theme.GrayDark
import theme.Primary
import utils.Spacer

@Composable
fun RegisterUserNameScreen(
    uiState: RegisterUserNameUiState.Data,
    onUserNameListener: (userName: String) -> Unit,
    onNext: (userName: String) -> Unit,
    onBack: () -> Unit
) {
    Column {
        Spacer(height = CustomDimensions.padding16)

        IconButton(
            onClick = onBack,
            colors = IconButtonDefaults.iconButtonColors(
                contentColor = Primary
            ),
            modifier = Modifier.padding(start = CustomDimensions.padding10)
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBackIos,
                contentDescription = "Voltar"
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = CustomDimensions.padding20)
        ) {
            Column(verticalArrangement = Arrangement.Top) {
                Spacer(height = CustomDimensions.padding16)

                Text(
                    text = "Identifique-se :)",
                    style = MaterialTheme.typography.titleLarge,
                    color = Primary
                )

                Spacer(height = CustomDimensions.padding16)

                Text(
                    text = "Todos já te conhecem, mas para que possa ser identificado no ranking, insira o nome pelo qual deseja ser chamado.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = GrayDark
                )

                Spacer(height = CustomDimensions.padding16)

                TextFieldCustom(
                    modifier = Modifier.fillMaxWidth(),
                    label = "Digite seu apelido/nome",
                    value = uiState.userName,
                    onChangeListener = onUserNameListener
                )
            }

            OutlinedButtonCustom(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(
                        bottom = CustomDimensions.padding40,
                    ),
                disabled = uiState.userName.isEmpty(),
                title = "Avançar",
                onButtonListener = {
                    onNext(uiState.userName)
                }
            )
        }
    }
}