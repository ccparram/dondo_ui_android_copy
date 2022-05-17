package com.dondo.ui.utils.extensions

import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import com.dondo.ui.utils.SafeClickListener

inline fun <reified T : ViewGroup.LayoutParams> View.layoutParams(block: T.() -> Unit) {
    if (layoutParams is T) block(layoutParams as T)
}

fun View.dpToPx(dp: Float): Int = context.dpToPx(dp)

fun View.getStringCompat(id: Int): String = this.context.getStringCompat(id)

fun View.getDrawableCompat(@DrawableRes id: Int): Drawable? = this.context.getDrawableCompat(id)

fun View.getColorCompat(@ColorRes id: Int): Int = this.context.getColorCompat(id)

fun View.getColorStateListCompat(@ColorRes id: Int): ColorStateList? = this.context.getColorStateListCompat(id)

fun View.applyMargin(left: Float? = null, top: Float? = null, right: Float? = null, bottom: Float? = null) {
    layoutParams<ViewGroup.MarginLayoutParams> {
        left?.run { leftMargin = dpToPx(this) }
        top?.run { topMargin = dpToPx(this) }
        right?.run { rightMargin = dpToPx(this) }
        bottom?.run { bottomMargin = dpToPx(this) }
    }
}

fun View.setSafeOnClickListener(onSafeClick: (View) -> Unit) {
    val safeClickListener = SafeClickListener(onSafeClick)
    setOnClickListener(safeClickListener)
}
