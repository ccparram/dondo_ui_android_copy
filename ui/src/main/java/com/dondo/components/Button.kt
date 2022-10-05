package com.dondo.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Badge
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.style.TextOverflow.Companion.Ellipsis
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dondo.components.ButtonType.Primary
import com.dondo.components.ButtonType.Secondary
import com.dondo.ui.R
import com.dondo.ui.utils.theme.DondoThemeContainer
import com.dondo.ui.utils.theme.PreviewContainer
import com.dondo.ui.utils.theme.conditional
import com.dondo.ui.utils.theme.fadedShadow
import com.dondo.ui.utils.theme.volumeBorder

@Composable
fun DondoButton(
    modifier: Modifier = Modifier,
    text: String,
    buttonType: ButtonType = Primary,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp)
            .conditional(
                condition = enabled && buttonType == Secondary,
                ifTrue = { volumeBorder() },
                ifFalse = { fadedShadow() }
            ),
        onClick = onClick,
        colors = buttonColors(buttonType),
        enabled = enabled,
        shape = RoundedCornerShape(24.dp),
        elevation = buttonElevation(buttonType),
        border = borderStroke(buttonType, enabled),
    ) {
        ButtonContent(text, buttonType, enabled)
    }
}

@Composable
private fun borderStroke(buttonType: ButtonType, enabled: Boolean) =
    when (buttonType) {
        Primary -> null
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
        overflow = TextOverflow.Visible,
        maxLines = 1,
        textAlign = Center,
    )
}

@Composable
private fun buttonElevation(buttonType: ButtonType) =
    when (buttonType) {
        Primary -> ButtonDefaults.elevation(defaultElevation = 4.dp)
        Secondary -> ButtonDefaults.elevation(defaultElevation = 0.dp)
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
}

@Composable
private fun styleTextColor(buttonType: ButtonType, enabled: Boolean) = if (!enabled) {
    DondoThemeContainer.colors.textDisabled
} else {
    when (buttonType) {
        Primary -> DondoThemeContainer.colors.primary
        Secondary -> DondoThemeContainer.colors.textSecondary
    }
}

enum class ButtonType { Primary, Secondary }

@Preview(showBackground = true, backgroundColor = 0xFCFBF0)
@Composable
private fun PrimaryButtonPreview() {
    PreviewContainer {
        DondoButton(text = "Primary button") {}
    }
}

@Preview(showBackground = true, backgroundColor = 0xFCFBF0)
@Composable
private fun SecondaryButtonPreview() {
    PreviewContainer {
        DondoButton(text = "Secondary button", buttonType = Secondary) {}
    }
}

@Preview(showBackground = true, backgroundColor = 0xFCFBF0)
@Composable
private fun PrimaryButtonWithFadedShadowPreview() {
    PreviewContainer {
        DondoButton(
            modifier = Modifier.fadedShadow(),
            text = "Primary button", buttonType = Primary
        ) {}
    }
}

@Preview(showBackground = true, backgroundColor = 0xFCFBF0)
@Composable
private fun DisabledButtonPreview() {
    PreviewContainer {
        DondoButton(text = "Disabled button", enabled = false) {}
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
            .fillMaxWidth()
            .volumeBorder(
                offsetX = 5f,
                offsetY = 15f,
                cornerRadiusX = 30f,
                cornerRadiusY = 30f
            ),
        onClick = onClick,
        colors = buttonColors(buttonType = Secondary),
        enabled = enabled,
        shape = RoundedCornerShape(10.dp),
        elevation = buttonElevation(buttonType = Secondary),
        border = borderStroke(buttonType = Secondary, enabled = enabled)
    ) {
        Row(
            modifier = modifier.fillMaxSize(),
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
                        modifier = modifier.padding(start = 4.dp),
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
                        modifier = modifier.padding(end = 12.dp),
                    ) {
                        Text(
                            maxLines = 1,
                            text = notificationBadgeText
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
private fun ProfileToolbarPreview() {
    PreviewContainer {
        ProfileToolbar(
            modifier = Modifier,
            clubId = 1,
            profilePicture = "",
            name = "Los mejor de Dondo con un texto más largo",
            backIcon = R.drawable.ic_back,
            onBackPress = {},
            rightButton = Pair(R.drawable.ic_vertical_dot_menu) {}
        )
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

@Composable
fun CircleIconButton(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    onClick: () -> Unit
) {
    OutlinedButton(
        modifier = modifier
            .size(40.dp)
            .volumeBorder(
                offsetX = 3f,
                offsetY = 10f,
                sizeWidthToSubstract = 6f,
                sizeHeightToSubstract = 6f
            ),
        onClick = { onClick() },
        shape = CircleShape,
        border = BorderStroke(1.dp, Black),
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.outlinedButtonColors(contentColor = Black)
    ) {
        Icon(painter = painterResource(id = icon), contentDescription = null)
    }
}

@Preview(showBackground = true, backgroundColor = 0xFCFBF0)
@Composable
private fun CircleIconButtonPreview() {
    PreviewContainer {
        CircleIconButton(
            icon = R.drawable.ic_share
        ) {
        }
    }
}

@Composable
fun CircleText(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    OutlinedButton(
        modifier = modifier
            .size(40.dp)
            .volumeBorder(
                offsetX = 3f,
                offsetY = 10f,
                sizeWidthToSubstract = 6f,
                sizeHeightToSubstract = 6f
            ),
        onClick = { onClick() },
        enabled = false,
        shape = CircleShape,
        border = BorderStroke(1.dp, Black),
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.outlinedButtonColors(contentColor = Black),
    ) {
        Text(
            text = text,
            fontSize = 10.sp,
            color = Black,
            maxLines = 1
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFCFBF0)
@Composable
private fun CircleTextButtonPreview() {
    PreviewContainer {
        CircleText(
            text = "+2.9k"
        ) {}
    }
}

@Composable
fun RoundedText(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    OutlinedButton(
        modifier = modifier
            .size(40.dp)
            .volumeBorder(
                offsetX = 3f,
                offsetY = 10f,
                sizeWidthToSubstract = 6f,
                sizeHeightToSubstract = 6f,
                cornerRadiusX = 30f,
                cornerRadiusY = 30f,
            )
            .clip(RoundedCornerShape(10.dp)),
        shape = RoundedCornerShape(10.dp),
        onClick = { onClick() },
        enabled = false,
        border = BorderStroke(1.dp, Black),
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.outlinedButtonColors(contentColor = Black)
    ) {
        Text(
            text = text,
            fontSize = 10.sp,
            color = Black,
            maxLines = 1
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFCFBF0)
@Composable
private fun RoundedTextButtonPreview() {
    PreviewContainer {
        RoundedText(
            text = "+2.9k"
        ) {}
    }
}
