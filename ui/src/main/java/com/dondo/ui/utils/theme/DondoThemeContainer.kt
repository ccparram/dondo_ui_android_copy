package com.dondo.ui.utils.theme

import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf

val LocalColors = staticCompositionLocalOf { dondoDefaultLightColors }
val LocalTypography = staticCompositionLocalOf { dondoTypography }

object DondoThemeContainer {
    // Retrieves the current colors
    val colors: DondoColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    // Retrieves the current typography
    val typography: Typography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current

}
