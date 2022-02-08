package com.dondo.ui.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.TypedValue
import android.view.LayoutInflater
import android.widget.EditText
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewbinding.ViewBinding

inline fun <T : ViewBinding> AppCompatActivity.viewBinding(crossinline bindingInflater: (LayoutInflater) -> T) =
    lazy(LazyThreadSafetyMode.NONE) {
        bindingInflater.invoke(layoutInflater)
    }

fun EditText.isEmpty(): Boolean {
    return this.text.toString().isBlank()
}

fun Context.dpToPx(dp: Float): Int =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.displayMetrics).toInt()

fun Context.getStringCompat(@StringRes id: Int): String = this.getString(id)

fun Context.getDrawableCompat(@DrawableRes id: Int): Drawable? = ContextCompat.getDrawable(this, id)

fun Context.getColorCompat(@ColorRes id: Int): Int = ContextCompat.getColor(this, id)
