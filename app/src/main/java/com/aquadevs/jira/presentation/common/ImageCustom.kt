package com.aquadevs.jira.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.aquadevs.jira.R

@Composable
fun ImageAsyncCustom(
    urlPicture: String,
    shape: Shape = CircleShape,
    size: Int = 100
) {
    if (urlPicture.isEmpty()) {
        Image(
            painter = painterResource(id = R.drawable.no_profile_picture),
            contentDescription = null,
            modifier = Modifier
                .clip(shape)
                .size(size.dp),
            contentScale = ContentScale.Crop
        )
    } else {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .crossfade(1000)
                .data(urlPicture)
                .build(),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier
                .clip(shape)
                .size(size.dp)
        )
    }
}