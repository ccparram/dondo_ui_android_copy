package com.dondo.ui.utils.theme

import androidx.compose.ui.graphics.Color

data class DondoColors(
    val primary: Color,
    val secondary: Color,
    val background: Color,
    val backgroundDisabled: Color,
    val textPrimary: Color,
    val textSecondary: Color,
    val textDisabled: Color,
    val error: Color
)

internal val dondoDefaultLightColors =
    DondoColors(
        primary = Color(0xFFFFFFFF),
        secondary = Color(0xFFFCFBF0),
        background = Color(0xFFFCFBF0),
        backgroundDisabled = Color(0xFFE7E7E7),
        textPrimary = Color(0xFFFFFFFF),
        textSecondary = Color(0xFF262626),
        textDisabled = Color(0xFF9D9D9D),
        error = Color(0xFFFA4D56),
    )

val Blank = Color(0xFFFFFFFF)
val Background = Color(0xFFFCFBF0)
val Gray1 = Color(0xFFE7E7E7)
val Gray2 = Color(0xFF7D7D7D)
val Gray3 = Color(0xFF262626)
val Info = Color(0xFF4589FF)
val Success = Color(0xFF77F16D)
val Warning = Color(0xFFFA4D56)
val Caution = Color(0xFFFDE744)
val Error = Warning
