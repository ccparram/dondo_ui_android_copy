package com.dondo.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
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
            .height(36.dp)
            .conditional(
                condition = buttonType == Secondary,
                ifTrue = { volumeBorder() },
            ),
        onClick = onClick,
        colors = buttonColors(buttonType),
        enabled = enabled,
        shape = RoundedCornerShape(24.dp),
        elevation = buttonElevation(buttonType),
        border = borderStroke(buttonType, enabled)
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
        backgroundColor = styleColor(buttonType),
        contentColor = DondoThemeContainer.colors.textPrimary,
        disabledBackgroundColor = DondoThemeContainer.colors.backgroundDisabled,
        disabledContentColor = DondoThemeContainer.colors.textDisabled
    )
    Secondary -> ButtonDefaults.buttonColors(
        backgroundColor = styleColor(buttonType),
        contentColor = styleColor(buttonType),
        disabledBackgroundColor = DondoThemeContainer.colors.backgroundDisabled,
        disabledContentColor = DondoThemeContainer.colors.textDisabled
    )
    Borderless -> ButtonDefaults.buttonColors(
        backgroundColor = styleColor(buttonType),
        contentColor = styleColor(buttonType),
        disabledBackgroundColor = DondoThemeContainer.colors.backgroundDisabled,
        disabledContentColor = DondoThemeContainer.colors.textDisabled
    )
}

@Composable
private fun styleColor(buttonType: ButtonType) =
    when (buttonType) {
        Primary -> DondoThemeContainer.colors.textSecondary
        Secondary -> DondoThemeContainer.colors.primary
        Borderless -> Transparent
    }

@Composable
private fun styleTextColor(buttonType: ButtonType, enabled: Boolean) = if(!enabled) {
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
        DondoButton(text = "Secondary button", buttonType = Secondary) {

        }
    }
}

@Preview
@Composable
private fun DisabledButtonPreview() {
    PreviewContainer {
        DondoButton(text = "Disabled button", enabled = false) {

        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFCFBF0)
@Composable
private fun BorderLessButtonPreview() {
    PreviewContainer {
        DondoButton(text = "Borderless button", buttonType = Borderless) {

        }
    }
}

@Composable
fun MenuButton(
    modifier: Modifier = Modifier,
    text: String,
    count: Int? = null,
    notificationBadgeText: String? = null,
    @DrawableRes rightIcon: Int,
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
        MenuButtonContent(
            text = text,
            count = count,
            notificationBadgeText = notificationBadgeText,
            rightIcon = rightIcon,
            enabled = enabled
        )
    }
}

@Composable
private fun MenuButtonContent(
    text: String,
    count: Int?,
    notificationBadgeText: String?,
    @DrawableRes rightIcon: Int,
    enabled: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = text,
            color = styleTextColor(Secondary, enabled),
            style = DondoThemeContainer.typography.button,
            overflow = Ellipsis,
            maxLines = 1,
        )
        Text(
            textAlign = TextAlign.Start,
            color = Color(0xFF7D7D7D),
            text = "($count)"
        )
        Badge(
            modifier = Modifier
                .wrapContentWidth()
                .weight(2f),
        ) {
            Text(
                maxLines = 1,
                text = notificationBadgeText ?: ""
            )
        }
        Image(
            modifier = Modifier
                .height(8.dp)
                .width(5.dp),
            painter = painterResource(id = rightIcon),
            contentDescription = "next",
            colorFilter = ColorFilter.tint(color = Color(0xFF1C1C1C))
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFCFBF0)
@Composable
private fun MenuButtonPreview() {
    Row(
        modifier = Modifier.padding(16.dp)
    ) {
        MenuButton(
            text = "\uD83D\uDCE7 Menu button",
            count = 2302,
            notificationBadgeText = "+9",
            rightIcon = R.drawable.ic_quantity_minus
        ) {
        }
    }
}

@Composable
fun ChipButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled,
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(1.dp, Color.Black),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
    ) {
        Text(
            textAlign = TextAlign.Start,
            text = "#$text",
            color = Color.Black
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFCFBF0)
@Composable
private fun ChipButtonPreview() {
    Row(
        modifier = Modifier.padding(16.dp)
    ) {
        ChipButton(text = "Fotografía") {
        }
    }
}
