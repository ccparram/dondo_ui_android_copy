package com.dondo.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Badge
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow.Companion.Ellipsis
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dondo.ui.R
import com.dondo.ui.utils.theme.Gray2
import com.dondo.ui.utils.theme.PreviewContainer

@Composable
fun ClubElement(
    modifier: Modifier = Modifier,
    clubId: Int,
    name: String,
    profilePicture: String,
    membersCount: String,
    accessType: String,
    itemsCount: String,
    amIMember: Boolean,
    unseenNotifications: String? = null,
    onClick: (Int) -> Unit
) {
    Row(
        modifier = modifier
            .wrapContentWidth()
    ) {
        Box(
            modifier = modifier
                .align(CenterVertically)
        ) {
            CircleShapedPicture(profilePicture = profilePicture)
            if (!unseenNotifications.isNullOrBlank()) {
                Badge(
                    modifier = modifier
                        .align(BottomEnd)
                ) {
                    Text(
                        maxLines = 1,
                        text = unseenNotifications
                    )
                }
            }
        }
        Column(
            modifier = modifier
                .padding(start = 16.dp)
                .align(CenterVertically),
            horizontalAlignment = Start
        ) {
            Text(
                modifier = modifier,
                text = name,
                fontSize = 14.sp,
                maxLines = 2,
                overflow = Ellipsis,
            )
            ClubInfo(
                modifier = modifier
                    .padding(top = 8.dp),
                membersCount = membersCount,
                accessType = accessType,
                itemsCount = itemsCount
            )
        }
        if (amIMember) {
            DondoButton(
                modifier = modifier
                    .padding(start = 16.dp)
                    .align(CenterVertically),
                text = stringResource(id = R.string.join),
                buttonType = ButtonType.Primary
            ) {
                onClick(clubId)
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFCFBF0)
@Composable
private fun ClubElementBeingMemberPreview() {
    PreviewContainer {
        ClubElement(name = "Hellfire, club de solo trueque Norte de Bogota y más alrededores",
            profilePicture = "https://picsum.photos/300/300",
            clubId = 2,
            membersCount = "23,434",
            accessType = "Privado",
            itemsCount = "43,344",
            amIMember = true,
            unseenNotifications = "+9",
            onClick = {}
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFCFBF0)
@Composable
private fun ClubElementNotBeingMemberPreview() {
    PreviewContainer {
        ClubElement(name = "Hellfire, club de solo trueque Norte de Bogota y más alrededores",
            profilePicture = "https://picsum.photos/300/300",
            clubId = 2,
            membersCount = "23,434",
            accessType = "Privado",
            itemsCount = "43,344",
            amIMember = false,
            unseenNotifications = "+9",
            onClick = {}
        )
    }
}

@Composable
fun ClubInfo(
    modifier: Modifier = Modifier,
    membersCount: String,
    accessType: String,
    itemsCount: String
) {
    Row {
        Icon(
            modifier = modifier
                .width(16.dp)
                .height(16.dp)
                .align(CenterVertically),
            painter = painterResource(id = R.drawable.ic_members),
            contentDescription = null,
            tint = Gray2
        )
        Text(
            modifier = modifier.padding(start = 8.dp),
            text = "($membersCount)",
            color = Gray2,
            fontSize = 12.sp
        )
        Icon(
            modifier = modifier
                .padding(start = 10.dp)
                .width(16.dp)
                .height(16.dp)
                .align(CenterVertically),
            painter = painterResource(id = R.drawable.ic_club),
            contentDescription = null,
            tint = Gray2
        )
        Text(
            modifier = modifier.padding(start = 8.dp),
            text = accessType,
            color = Gray2,
            fontSize = 12.sp
        )
        Icon(
            modifier = modifier
                .padding(start = 10.dp)
                .width(16.dp)
                .height(16.dp)
                .align(CenterVertically),
            painter = painterResource(id = R.drawable.ic_items),
            contentDescription = null,
            tint = Gray2
        )
        Text(
            modifier = modifier.padding(start = 8.dp),
            text = "($itemsCount)",
            color = Gray2,
            fontSize = 12.sp
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFCFBF0)
@Composable
private fun ClubInfoPreview() {
    PreviewContainer {
        ClubInfo(
            membersCount = "123,234",
            itemsCount = "432,344",
            accessType = "Público"
        )
    }
}

@Composable
fun ProfileToolbar(
    modifier: Modifier,
    clubId: Int,
    profilePicture: String,
    name: String,
    onBackPress: () -> Unit,
    firstAction: Pair<Int, (Int) -> Unit>?,
    secondAction: Pair<Int, (Int) -> Unit>?,
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = CenterVertically
    ) {
        Row(
            modifier = modifier
                .wrapContentWidth(),
            verticalAlignment = CenterVertically
        ) {
            IconButton(
                onClick = { onBackPress() }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = null,
                    tint = Gray2
                )
            }
            CircleShapedPicture(
                profilePicture = profilePicture,
                size = 40.dp
            )
            Text(
                modifier = modifier
                    .padding(14.dp),
                textAlign = TextAlign.Start,
                text = name,
                maxLines = 1,
                overflow = Ellipsis
            )
        }
        Row {
            firstAction?.let {
                // first is the icon
                // second is the onClick
                RoundedIconButton(
                    modifier = modifier,
                    icon = firstAction.first
                ) {
                    firstAction.second(clubId)
                }
            }
            secondAction?.let {
                RoundedIconButton(
                    modifier = modifier.padding(start = 8.dp),
                    icon = secondAction.first
                ) {
                    secondAction.second(clubId)
                }
            }
        }

    }
}

@Preview(showBackground = true, backgroundColor = 0xFCFBF0)
@Composable
private fun ClubToolbarPreview() {
    PreviewContainer {
        ProfileToolbar(
            modifier = Modifier,
            clubId = 1,
            profilePicture = "",
            name = "Los mejor de Dondo",
            onBackPress = {},
            firstAction = Pair(R.drawable.ic_share) {},
            secondAction = Pair(R.drawable.ic_vertical_dot_menu) {}
        )
    }
}