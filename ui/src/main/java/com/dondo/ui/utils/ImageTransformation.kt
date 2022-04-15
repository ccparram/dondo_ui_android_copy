package com.dondo.ui.utils

import androidx.annotation.Px

sealed class ImageTransformation {
    object CircleCropTransformation : ImageTransformation()
    data class RoundedCornerTransformation(@Px val radius: Float) : ImageTransformation()
}
