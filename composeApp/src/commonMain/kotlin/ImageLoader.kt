import androidx.compose.ui.graphics.ImageBitmap
import org.koin.core.component.KoinComponent

expect class ImageLoader(): KoinComponent {
    suspend fun loadImageFromBytes(uri: String): ImageBitmap?
}