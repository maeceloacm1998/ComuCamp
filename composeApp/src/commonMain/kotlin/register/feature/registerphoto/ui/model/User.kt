package register.feature.registerphoto.ui.model

import androidx.compose.ui.graphics.ImageBitmap

data class User(
    val userName: String,
    val photoUrl: ImageBitmap?
)