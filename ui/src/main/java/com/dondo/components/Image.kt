package com.dondo.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.dondo.ui.R
import com.dondo.ui.utils.theme.PreviewContainer

@Composable
fun CircleShapedPicture(
    modifier: Modifier = Modifier,
    profilePicture: String,
    size: Dp = 50.dp
) {
    AsyncImage(
        modifier = modifier
            .height(size)
            .width(size)
            .clip(CircleShape),
        contentScale = ContentScale.Crop,
        model = ImageRequest.Builder(LocalContext.current)
            .data(profilePicture)
            .error(R.drawable.placeholder_profile_picture)
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
        contentScale = ContentScale.Crop,
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
        CircleShapedPicture(profilePicture = "https://picsum.photos/300/300")
    }
}

@Preview(showBackground = true, backgroundColor = 0xFCFBF0)
@Composable
private fun RoundedCornerPicturePreview() {
    PreviewContainer {
        RoundedCornerPicture(profilePicture = "https://picsum.photos/300/300")
    }
}
