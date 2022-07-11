package com.dondo.ui.utils.extensions

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.webkit.URLUtil
import android.widget.ImageView
import androidx.annotation.DrawableRes
import coil.ImageLoader
import coil.disk.DiskCache
import coil.load
import coil.memory.MemoryCache
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import coil.transform.Transformation
import com.dondo.ui.utils.ImageTransformation
import kotlinx.coroutines.Dispatchers
import java.io.File

fun ImageView.loadImage(
    uri: String?,
    errorResId: Int? = null,
    imageTransformations: List<ImageTransformation>? = null
) {
    if (URLUtil.isValidUrl(uri)) {
        this.load(uri, imageLoader = CustomImageLoader.getInstance(this.context)) {
            errorResId?.let {
                fallback(it)
                error(it)
            }
            transformations(getCoilTransformations(imageTransformations))
        }
    } else {
        uri?.let {
            this.load(File(uri)) {
                errorResId?.let {
                    fallback(it)
                    error(it)
                }
                transformations(getCoilTransformations(imageTransformations))
            }
        }
    }
}

fun ImageView.loadImage(
    image: Bitmap?,
    errorResId: Int? = null,
    imageTransformations: List<ImageTransformation>? = null
) {
    this.load(image) {
        errorResId?.let {
            fallback(it)
            error(it)
        }
        transformations(getCoilTransformations(imageTransformations))
    }
}

fun ImageView.loadImage(
    drawable: Drawable?,
    @DrawableRes errorResId: Int? = null,
    imageTransformations: List<ImageTransformation>? = null
) {
    this.load(drawable, imageLoader = CustomImageLoader.getInstance(this.context)) {
        errorResId?.let {
            fallback(it)
            error(it)
        }
        transformations(getCoilTransformations(imageTransformations))
    }
}

fun ImageView.loadImage(
    @DrawableRes drawableResId: Int,
    @DrawableRes errorResId: Int? = null,
    imageTransformations: List<ImageTransformation>? = null
) {
    this.load(drawableResId, imageLoader = CustomImageLoader.getInstance(this.context)) {
        errorResId?.let {
            fallback(it)
            error(it)
        }
        transformations(getCoilTransformations(imageTransformations))
    }
}

private fun getCoilTransformations(transformations: List<ImageTransformation>?): List<Transformation> {
    return transformations?.map {
        when (it) {
            is ImageTransformation.CircleCropTransformation -> CircleCropTransformation()
            is ImageTransformation.RoundedCornerTransformation -> RoundedCornersTransformation(it.radius)
        }
    } ?: emptyList()
}

object CustomImageLoader {
    private lateinit var instance: ImageLoader

    fun getInstance(context: Context): ImageLoader {
        return if (::instance.isInitialized) {
            instance
        } else {
            instance = ImageLoader.Builder(context)
                .dispatcher(Dispatchers.Default)
                .memoryCache {
                    MemoryCache.Builder(context)
                        .maxSizePercent(0.3)
                        .build()
                }
                .diskCache {
                    DiskCache.Builder()
                        .directory(context.cacheDir.resolve("image_cache"))
                        .maxSizePercent(0.3)
                        .build()
                }
                .crossfade(true)
                .build()

            instance
        }
    }
}
