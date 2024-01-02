package org.app.project.utils.imageloader

import android.content.Context
import androidx.compose.ui.graphics.ImageBitmap

interface ImageLoader {
    suspend fun loadImageFromBytes(uri: String): ImageBitmap?
}