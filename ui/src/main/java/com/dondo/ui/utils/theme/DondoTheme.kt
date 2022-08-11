package com.dondo.ui.utils.theme

import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun DondoTheme(
    colors: DondoColors = DondoThemeContainer.colors,
    typography: Typography = DondoThemeContainer.typography,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalColors provides colors,
        LocalTypography provides typography
    ) {
        content()
    }
}
