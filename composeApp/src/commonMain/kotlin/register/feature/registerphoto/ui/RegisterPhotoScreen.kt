package register.feature.registerphoto.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import components.button.OutlinedButtonCustom
import register.feature.registerphoto.ui.model.User
import theme.Background
import theme.CustomDimensions
import theme.GrayDark
import theme.Primary
import utils.Spacer

@Composable
fun RegisterPhotoScreen(
    uiState: RegisterPhotoUiState.Data,
    onFinish: (user: User) -> Unit,
    onOpenPhoto: () -> Unit,
    onBack: () -> Unit,
) {
    Column(
        modifier = Modifier
            .background(Background)
            .fillMaxSize()
    ) {
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
                    text = "Apresente-se ao acampamento :D",
                    style = MaterialTheme.typography.titleLarge,
                    color = Primary
                )

                Spacer(height = CustomDimensions.padding16)

                Text(
                    text = "Apresente-se ao acampamento e mostre a sua personalidade!",
                    style = MaterialTheme.typography.bodyLarge,
                    color = GrayDark
                )

                Spacer(height = CustomDimensions.padding16)
            }

            PhotoContainer(
                modifier = Modifier.align(Alignment.Center),
                photoUrl = uiState.photoUrl,
                onOpenPhoto = onOpenPhoto
            )

            OutlinedButtonCustom(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(
                        bottom = CustomDimensions.padding40,
                    ),
                disabled = uiState.photoUrl != null,
                title = "Finalizar",
                onButtonListener = {
                    onFinish(
                        User(
                            userName = uiState.userName,
                            photoUrl = uiState.photoUrl
                        )
                    )
                }
            )
        }
    }
}

@Composable
private fun PhotoContainer(
    modifier: Modifier = Modifier,
    photoUrl: ImageBitmap?,
    onOpenPhoto: () -> Unit,
) {
    IconButton(
        modifier = modifier
            .size(
                height = CustomDimensions.padding300,
                width = CustomDimensions.padding300
            )
            .background(
                color = Color.White,
                shape = RoundedCornerShape(
                    CustomDimensions.padding300
                )
            )
            .border(
                CustomDimensions.padding2,
                Primary,
                shape = RoundedCornerShape(CustomDimensions.padding300)
            ),
        onClick = onOpenPhoto
    ) {
        if (photoUrl != null) {
            Image(
                bitmap = photoUrl,
                contentDescription = "imagem selecionada",
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop
            )
        } else {
            Column {
                Icon(
                    imageVector = Icons.Filled.PhotoCamera,
                    tint = Primary,
                    contentDescription = "Camera",
                    modifier = Modifier.size(CustomDimensions.padding80)
                )

                Text(
                    text = "Tirar foto",
                    style = MaterialTheme.typography.titleMedium,
                    color = Primary
                )
            }
        }
    }
}