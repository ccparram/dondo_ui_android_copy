package com.dondo.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Badge
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.text.style.TextOverflow.Companion.Ellipsis
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dondo.components.ButtonType.Borderless
import com.dondo.components.ButtonType.Primary
import com.dondo.components.ButtonType.Secondary
import com.dondo.ui.R
import com.dondo.ui.utils.theme.DondoThemeContainer
import com.dondo.ui.utils.theme.PreviewContainer
import com.dondo.ui.utils.theme.conditional
import com.dondo.ui.utils.theme.volumeBorder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun DondoButton(
    modifier: Modifier = Modifier,
    text: String,
    buttonType: ButtonType = Primary,
    enabled: Boolean = true,
    isFromDualButton: Boolean = false,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier
            .conditional(
                condition = isFromDualButton,
                ifTrue = { wrapContentWidth() },
                ifFalse = { fillMaxWidth() },
            )
            .height(40.dp)
            .conditional(
                condition = enabled && buttonType == Secondary,
                ifTrue = { volumeBorder() },
            ),
        onClick = onClick,
        colors = buttonColors(buttonType),
        enabled = enabled,
        shape = RoundedCornerShape(24.dp),
        elevation = buttonElevation(buttonType),
        border = borderStroke(buttonType, enabled),
        interactionSource = if (buttonType == Borderless) NoRippleInteractionSource() else remember { MutableInteractionSource() },
    ) {
        ButtonContent(text, buttonType, enabled)
    }
}

@Composable
private fun borderStroke(buttonType: ButtonType, enabled: Boolean) =
    when (buttonType) {
        Primary, Borderless -> null
        Secondary -> BorderStroke(
            1.dp,
            if (enabled) DondoThemeContainer.colors.textSecondary else Transparent
        )
    }

@Composable
private fun ButtonContent(
    text: String,
    buttonType: ButtonType,
    enabled: Boolean
) {
    Text(
        text = text,
        color = styleTextColor(buttonType, enabled),
        style = DondoThemeContainer.typography.button,
        overflow = Ellipsis,
        maxLines = 1,
        textAlign = Center,
    )
}

@Composable
private fun buttonElevation(buttonType: ButtonType) =
    when (buttonType) {
        Primary -> ButtonDefaults.elevation(defaultElevation = 4.dp)
        Secondary, Borderless -> ButtonDefaults.elevation(defaultElevation = 0.dp)
    }


@Composable
private fun buttonColors(buttonType: ButtonType) = when (buttonType) {
    Primary -> ButtonDefaults.buttonColors(
        backgroundColor = DondoThemeContainer.colors.textSecondary,
        contentColor = DondoThemeContainer.colors.textPrimary,
        disabledBackgroundColor = DondoThemeContainer.colors.backgroundDisabled,
        disabledContentColor = DondoThemeContainer.colors.textDisabled
    )
    Secondary -> ButtonDefaults.buttonColors(
        backgroundColor = DondoThemeContainer.colors.primary,
        contentColor = DondoThemeContainer.colors.primary,
        disabledBackgroundColor = DondoThemeContainer.colors.backgroundDisabled,
        disabledContentColor = DondoThemeContainer.colors.textDisabled
    )
    Borderless -> ButtonDefaults.buttonColors(
        backgroundColor = Transparent,
        contentColor = Transparent,
        disabledBackgroundColor = Transparent,
        disabledContentColor = Transparent
    )
}

@Composable
private fun styleTextColor(buttonType: ButtonType, enabled: Boolean) = if (!enabled) {
    DondoThemeContainer.colors.textDisabled
} else {
    when (buttonType) {
        Primary -> DondoThemeContainer.colors.primary
        Secondary -> DondoThemeContainer.colors.textSecondary
        Borderless -> DondoThemeContainer.colors.textSecondary
    }
}

enum class ButtonType { Primary, Secondary, Borderless }

@Preview(showBackground = true, backgroundColor = 0xFCFBF0)
@Composable
private fun PrimaryButtonPreview() {
    PreviewContainer {
        DondoButton(text = "Primary button") {

        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFCFBF0)
@Composable
private fun SecondaryButtonPreview() {
    PreviewContainer {
        DondoButton(text = "Secondary button", buttonType = Secondary) { }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFCFBF0)
@Composable
private fun DisabledButtonPreview() {
    PreviewContainer {
        DondoButton(text = "Disabled button", enabled = false) { }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFCFBF0)
@Composable
private fun BorderLessButtonPreview() {
    PreviewContainer {
        DondoButton(text = "Borderless button", buttonType = Borderless) { }
    }
}

@Composable
fun MenuButton(
    modifier: Modifier = Modifier,
    text: String,
    count: String? = null,
    notificationBadgeText: String? = null,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier
            .height(60.dp)
            .fillMaxWidth(),
        onClick = onClick,
        colors = buttonColors(buttonType = Secondary),
        enabled = enabled,
        shape = RoundedCornerShape(10.dp),
        elevation = buttonElevation(buttonType = Secondary),
        border = borderStroke(buttonType = Secondary, enabled = enabled)
    ) {
        Row(
            modifier = modifier
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row {
                Text(
                    text = text,
                    color = styleTextColor(Secondary, enabled),
                    style = DondoThemeContainer.typography.button,
                    overflow = Ellipsis,
                    maxLines = 1,
                )
                if (!count.isNullOrBlank()) {
                    Text(
                        modifier = modifier
                            .padding(start = 4.dp),
                        textAlign = TextAlign.Start,
                        color = Color(0xFF7D7D7D),
                        text = "($count)"
                    )
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (!notificationBadgeText.isNullOrBlank()) {
                    Badge(
                        modifier = modifier
                            .padding(end = 12.dp),
                    ) {
                        Text(
                            maxLines = 1,
                            text = notificationBadgeText ?: ""
                        )
                    }
                }
                Image(
                    modifier = modifier
                        .height(8.dp)
                        .width(8.dp),
                    painter = painterResource(id = R.drawable.ic_next),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(color = Color(0xFF1C1C1C))
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFCFBF0)
@Composable
private fun MenuButtonSimplePreview() {
    PreviewContainer {
        MenuButton(
            text = "\uD83D\uDCE7 Menu button",
            count = null,
            notificationBadgeText = ""
        ) {
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFCFBF0)
@Composable
private fun MenuButtonComplexPreview() {
    PreviewContainer {
        MenuButton(
            text = "\uD83D\uDCE7 Menu button",
            count = "232,434",
            notificationBadgeText = "+9"
        ) {
        }
    }
}

// Used to remove ripple effect on Button when is ButtonType.Borderless
// Reference: https://semicolonspace.com/jetpack-compose-disable-ripple-effect/
class NoRippleInteractionSource : MutableInteractionSource {

    override val interactions: Flow<Interaction> = emptyFlow()

    override suspend fun emit(interaction: Interaction) {}

    override fun tryEmit(interaction: Interaction) = true
}
