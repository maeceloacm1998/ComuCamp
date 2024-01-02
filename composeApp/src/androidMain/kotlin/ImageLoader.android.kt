
import androidx.compose.ui.graphics.ImageBitmap
import org.app.project.utils.imageloader.ImageLoader
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


actual class ImageLoader actual constructor() : KoinComponent {
    private val injectionModule by inject<ImageLoader>()

    actual suspend fun loadImageFromBytes(uri: String): ImageBitmap? {
        return injectionModule.loadImageFromBytes(uri)
    }
}