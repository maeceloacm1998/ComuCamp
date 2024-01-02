package org.app.project.utils.imageloader

import android.content.ContentResolver
import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream

class ImageLoaderImpl(private val context: Context): ImageLoader {
    override suspend fun loadImageFromBytes(uri: String): ImageBitmap? {
        return withContext(Dispatchers.IO) {
            try {
                return@withContext try {
                    val contentResolver: ContentResolver = context.contentResolver
                    val inputStream: InputStream? = contentResolver.openInputStream(Uri.parse(uri))
                    if (inputStream != null) {
                        val bitmap = BitmapFactory.decodeStream(inputStream)
                        inputStream.close()
                        bitmap?.asImageBitmap()
                    } else {
                        null
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    null
                }
            } catch (e: Exception) {
                e.printStackTrace()
                return@withContext null
            }
        }
    }
}