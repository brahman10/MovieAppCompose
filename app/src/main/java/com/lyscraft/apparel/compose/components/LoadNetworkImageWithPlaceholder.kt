package com.lyscraft.apparel.compose.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.lyscraft.apparel.R

@Composable
fun LoadNetworkImageWithPlaceholder(
    baseUrl: String,
    imageUrl: String?,
    aspectRatio: Float = 0.66f,
    modifier: Modifier = Modifier
) {
    val imagePainter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .crossfade(true)
            .data("$baseUrl$imageUrl")
            .placeholder(R.drawable.loading)
            .error(R.drawable.loading)
            .scale(coil.size.Scale.FILL)
            .build()
    )

    Image(
        painter = imagePainter,
        contentDescription = null,
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(aspectRatio)
            .clip(RoundedCornerShape(8.dp)),
        contentScale = ContentScale.Crop
    )
}