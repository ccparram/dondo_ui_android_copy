package com.dondo.ui.utils.theme

import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.font.FontWeight.Companion.Normal
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.unit.sp
import com.dondo.ui.R

val Inter = FontFamily(
    Font(R.font.inter_regular, weight = Normal),
    Font(R.font.inter_semi_bold, weight = SemiBold),
    Font(R.font.inter_bold, weight = Bold)
)

// Set of Material typography styles to start with
val dondoTypography = Typography(
    defaultFontFamily= Inter,
    h1 = TextStyle(
        fontWeight = Bold,
        fontSize = 28.sp
    ),
    h2 = TextStyle(
        fontWeight = Bold,
        fontSize = 20.sp
    ),
    h3 = TextStyle(
        fontWeight = Bold,
        fontSize = 16.sp
    ),
    h4 = TextStyle(
        fontWeight = Bold,
        fontSize = 14.sp
    ),
    h5 = TextStyle(
        fontWeight = Bold,
        fontSize = 12.sp
    ),
    body1 = TextStyle(
        fontWeight = Normal,
        fontSize = 14.sp
    ),
    body2 = TextStyle(
        fontWeight = Normal,
        fontSize = 12.sp
    ),
    button = TextStyle(
        fontWeight = SemiBold,
        fontSize = 14.sp
    )
)

val Typography.body3: TextStyle
    @Composable
    get() {
        return  TextStyle(
            fontWeight = Normal,
            fontSize = 18.sp
        )
    }

val Typography.body4: TextStyle
    @Composable
    get() {
        return  TextStyle(
            fontWeight = Normal,
            fontSize = 16.sp
        )
    }

val Typography.body5: TextStyle
    @Composable
    get() {
        return  TextStyle(
            fontWeight = Normal,
            fontSize = 12.sp
        )
    }


