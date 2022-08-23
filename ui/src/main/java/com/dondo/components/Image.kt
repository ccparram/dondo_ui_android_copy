package com.dondo.components

import androidx.compose.foundation.layout.height
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
import com.dondo.ui.utils.theme.PreviewContainer

@Composable
fun CircleShapedPicture(
    modifier: Modifier = Modifier,
    profilePicture: String
) {
    AsyncImage(
        modifier = modifier
            .height(50.dp)
            .width(50.dp)
            .clip(CircleShape),
        model = ImageRequest.Builder(LocalContext.current)
            .data(profilePicture)
            .crossfade(true)
            .build(),
        contentDescription = null
    )
}

@Composable
fun RoundedCornerPicture(
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
private fun CircleShapedPicturePreview() {
    PreviewContainer {
        CircleShapedPicture(Modifier, "https://picsum.photos/300/300")
    }
}

@Preview(showBackground = true, backgroundColor = 0xFCFBF0)
@Composable
private fun RoundedCornerPicturePreview() {
    PreviewContainer {
        RoundedCornerPicture(Modifier, "https://picsum.photos/300/300")
    }
}
