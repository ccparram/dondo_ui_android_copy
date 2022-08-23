package com.dondo.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow.Companion.Ellipsis
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dondo.components.ButtonType.Secondary
import com.dondo.ui.R
import com.dondo.ui.utils.theme.DondoThemeContainer
import com.dondo.ui.utils.theme.Gray2
import com.dondo.ui.utils.theme.PreviewContainer

@Composable
fun UserElement(
    modifier: Modifier = Modifier,
    userId: Int,
    name: String,
    profilePicture: String,
    reviewInfo: String,
    lastSeen: String,
    amIFollowing: Boolean,
    onClick: (Int) -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = CenterVertically
    ) {
        Row {
            CircleShapedPicture(profilePicture = profilePicture)
            Column(
                modifier = modifier
                    .padding(start = 16.dp)
                    .align(CenterVertically),
                horizontalAlignment = Start
            ) {
                Row {
                    Text(
                        modifier = modifier,
                        text = name,
                        maxLines = 1,
                        overflow = Ellipsis,
                    )
                    Text(
                        modifier = modifier.padding(start = 8.dp),
                        text = reviewInfo,
                        maxLines = 1,
                        color = Gray2
                    )
                }
                Text(
                    modifier = modifier.padding(top = 8.dp),
                    text = lastSeen,
                    maxLines = 1,
                    color = Gray2,
                )
            }
        }
        if (amIFollowing) {
            ClickableText(
                modifier = modifier
                    .padding(horizontal = 28.dp)
                    .align(CenterVertically),
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = DondoThemeContainer.colors.textDisabled)) {
                        append(stringResource(R.string.following))
                    }
                },
                style = DondoThemeContainer.typography.button,
                overflow = Ellipsis,
                maxLines = 1,
            ) {
                onClick(userId)
            }
        } else {
            DondoButton(
                modifier = modifier
                    .padding(start = 16.dp)
                    .align(CenterVertically),
                text = stringResource(R.string.follow),
                buttonType = Secondary
            ) {
                onClick(userId)
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFCFBF0)
@Composable
private fun UserElementPreview() {
    PreviewContainer {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            UserElement(
                userId = 1262,
                name = "Esterfulvia",
                profilePicture = "Una imagen.jpg",
                reviewInfo = "⭐ 4.3 (56)",
                lastSeen = "\uD83D\uDFE2 En linea",
                amIFollowing = false,
                onClick = {}
            )
            UserElement(
                userId = 1262,
                name = "Vince",
                profilePicture = "Una imagen.jpg",
                reviewInfo = "⭐ 4.3 (56)",
                lastSeen = "\uD83D\uDFE2 En linea",
                amIFollowing = true,
                onClick = {}
            )
        }
    }
}
