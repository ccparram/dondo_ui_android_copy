package com.dondo.ui.formfields

import android.util.AttributeSet

interface FormField {

    fun setup(attrs: AttributeSet?)

    fun isValid(): Boolean
}
