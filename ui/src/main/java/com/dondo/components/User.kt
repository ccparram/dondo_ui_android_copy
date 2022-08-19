package com.dondo.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dondo.components.ButtonType.Borderless
import com.dondo.components.ButtonType.Secondary
import com.dondo.ui.R
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
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    modifier = modifier,
                    text = reviewInfo,
                    maxLines = 2,
                    color = Gray2
                )
            }
            Text(
                modifier = modifier,
                text = lastSeen,
                maxLines = 2,
                color = Gray2,
            )
        }
        if (amIFollowing) {
            DondoButton(
                modifier = modifier
                    .padding(start = 16.dp)
                    .align(CenterVertically),
                text = stringResource(R.string.following),
                buttonType = Borderless
            ) {
                onClick(userId)
            }
        } else {
            DondoButton(
                modifier = modifier
                    .padding(start = 16.dp)
                    .align(CenterVertically),
                text = stringResource(R.string.follow) + "asdfasdf",
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
                name = "Vince",
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
