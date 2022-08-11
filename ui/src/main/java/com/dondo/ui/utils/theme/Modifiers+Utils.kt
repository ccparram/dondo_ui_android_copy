package com.dondo.ui.utils.theme

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
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

fun Modifier.darkBorder(): Modifier =
    border(
        width = 1.dp,
        shape = Shapes.large,
        color = Gray3
    )

fun Modifier.volumeBorder(): Modifier =
    drawBehind {
        drawRoundRect(
            color = Gray3,
            cornerRadius = CornerRadius(60f,60f),
            topLeft = Offset(15f,20f),
            size = Size(size.width-4f, size.height-10f),
        )
    }.padding(bottom = 1.dp, end = 2.dp)

