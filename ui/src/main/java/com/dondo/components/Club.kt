package com.dondo.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
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
import com.dondo.components.ImageShape.Circle
import com.dondo.components.ImageShape.Rounded
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
        ClubElement(
            name = "Hellfire, club de solo trueque Norte de Bogota y más alrededores",
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
        ClubElement(
            name = "Hellfire, club de solo trueque Norte de Bogota y más alrededores",
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
    @DrawableRes backIcon: Int,
    onBackPress: () -> Unit,
    rightButton: Pair<Int, (Int) -> Unit>? = null,
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
                    painter = painterResource(id = backIcon),
                    contentDescription = null,
                    tint = Gray2
                )
            }
            CircleShapedPicture(
                profilePicture = profilePicture,
                size = 40.dp
            )
            Row(
                modifier = Modifier
                    .padding(14.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    modifier = modifier,
                    textAlign = TextAlign.Start,
                    color = Gray2,
                    text = name,
                    maxLines = 2,
                    overflow = Ellipsis
                )
            }
        }
        Row(
            modifier = modifier
                .padding(end = 16.dp)
        ) {
            rightButton?.let {
                CircleIconButton(
                    modifier = modifier.padding(start = 8.dp),
                    icon = rightButton.first
                ) {
                    rightButton.second(clubId)
                }
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
            name = "Los mejor de Dondo",
            backIcon = R.drawable.ic_back,
            onBackPress = {},
            rightButton = Pair(R.drawable.ic_vertical_dot_menu) {}
        )
    }
}

enum class ImageShape { Rounded, Circle }

@Composable
fun ClubImages(
    modifier: Modifier,
    imageShape: ImageShape,
    shouldShowCount: Boolean,
    formattedCount: String?,
    pictures: List<String>,
    onClick: () -> Unit
) {
    Column {
        Row(
            modifier = Modifier
                .padding(end = 6.dp, bottom = 8.dp)
                .clickable { onClick() }
        ) {
            when (imageShape) {
                Rounded -> {
                    pictures.forEach { itemPic ->
                        RoundedCornerPicture(
                            modifier
                                .padding(end = 6.dp),
                            profilePicture = itemPic
                        )
                    }
                    if (shouldShowCount && !formattedCount.isNullOrBlank()) {
                        RoundedText(
                            modifier = modifier,
                            text = formattedCount
                        )
                    }
                }
                Circle -> {
                    pictures.forEach { itemPic ->
                        CircleShapedPicture(
                            modifier.padding(end = 6.dp),
                            profilePicture = itemPic,
                            size = 40.dp
                        )
                    }
                    if (shouldShowCount && !formattedCount.isNullOrBlank()) {
                        CircleText(
                            modifier = modifier,
                            text = formattedCount
                        ) { onClick() }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFCFBF0)
@Composable
private fun ClubImagesPreview() {
    PreviewContainer {
        ClubImages(
            modifier = Modifier,
            imageShape = Circle,
            shouldShowCount = true,
            formattedCount = "+3,9M",
            pictures = listOf("", "")
        ) {}
    }
}