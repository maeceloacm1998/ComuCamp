import androidx.compose.ui.graphics.ImageBitmap
import org.koin.core.component.KoinComponent

actual class ImageLoader actual constructor() : KoinComponent {
    actual suspend fun loadImageFromBytes(uri: String): ImageBitmap? {
        TODO("Not yet implemented")
    }
}