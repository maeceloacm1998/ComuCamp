package org.app.project.utils.imageloader

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

object ImageLoaderModule {
    val modules = module {
        single<ImageLoader> { ImageLoaderImpl(context = androidContext()) }
    }
}