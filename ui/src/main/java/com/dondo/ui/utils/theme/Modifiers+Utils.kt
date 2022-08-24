package com.dondo.ui.utils.theme

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun PreviewContainer(content: @Composable () -> Unit) {
    Row(modifier = Modifier.padding(16.dp)) {
        content()
    }
}

/**
 * Modifiers
 */

// Reference: https://stackoverflow.com/a/72554087/3729124
fun Modifier.conditional(
    condition: Boolean,
    ifTrue: Modifier.() -> Modifier,
    ifFalse: (Modifier.() -> Modifier)? = null
): Modifier =
    when {
        condition -> then(ifTrue(this))
        ifFalse != null -> then(ifFalse(this))
        else -> this
    }

fun Modifier.darkBorder(width: Dp = 1.dp, shape: CornerBasedShape = Shapes.large, color: Color = Gray3): Modifier =
    border(
        width = width,
        shape = shape,
        color = color
    )

fun Modifier.volumeBorder(
    offsetX: Float = 15f,
    offsetY: Float = 20f,
    sizeWidthToSubstract: Float = 4f,
    sizeHeightToSubstract: Float = 10f
): Modifier =
    drawBehind {
        drawRoundRect(
            color = Gray3,
            cornerRadius = CornerRadius(60f, 60f),
            topLeft = Offset(offsetX, offsetY),
            size = Size(size.width - sizeWidthToSubstract, size.height - sizeHeightToSubstract),
        )
    }.padding(bottom = 1.dp, end = 2.dp)
