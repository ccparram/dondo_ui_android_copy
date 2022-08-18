package com.dondo.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun ClubItemPicture(
    modifier: Modifier = Modifier,
    profilePicture: String
) {
    AsyncImage(
        modifier = modifier
            .height(40.dp)
            .width(40.dp)
            .clip(CircleShape),
        model = ImageRequest.Builder(LocalContext.current)
            .data(profilePicture)
            .crossfade(true)
            .build(),
        contentDescription = null
    )
}

@Composable
fun ClubMemberPicture(
    modifier: Modifier = Modifier,
    profilePicture: String
) {
    AsyncImage(
        modifier = modifier
            .height(40.dp)
            .width(40.dp)
            .clip(RoundedCornerShape(10.dp)),
        model = ImageRequest.Builder(LocalContext.current)
            .data(profilePicture)
            .crossfade(true)
            .build(),
        contentDescription = null
    )
}

@Preview(showBackground = true, backgroundColor = 0xFCFBF0)
@Composable
private fun ClubItemPicturePreview() {
    Row(
        modifier = Modifier.padding(16.dp)
    ) {
        ClubItemPicture(Modifier, "https://picsum.photos/300/300")
    }
}

@Preview(showBackground = true, backgroundColor = 0xFCFBF0)
@Composable
private fun ClubMemberPicturePreview() {
    Row(
        modifier = Modifier.padding(16.dp)
    ) {
        ClubMemberPicture(Modifier, "https://picsum.photos/300/300")
    }
}