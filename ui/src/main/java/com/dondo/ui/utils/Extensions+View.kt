package com.dondo.ui.utils

import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes

fun View.dpToPx(dp: Float): Int = context.dpToPx(dp)

fun View.getStringCompat(id: Int): String = this.context.getStringCompat(id)

fun View.getDrawableCompat(@DrawableRes id: Int): Drawable? = this.context.getDrawableCompat(id)

fun View.getColorCompat(@ColorRes id: Int): Int = this.context.getColorCompat(id)
