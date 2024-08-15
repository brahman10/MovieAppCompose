package com.lyscraft.apparel.compose.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerEffect(modifier: Modifier = Modifier) {
    val shimmerColors = listOf(
        Color.LightGray.copy(alpha = 0.6f),
        Color.LightGray.copy(alpha = 0.2f),
        Color.LightGray.copy(alpha = 0.6f)
    )

    val transition = rememberInfiniteTransition(label = "Shimmer")
    val translateAnim = transition.animateFloat(
        initialValue = 0f, targetValue = 1000f, animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1200, easing = FastOutSlowInEasing
            )
        ), label = "Shimmer"
    )

    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset(translateAnim.value, 0f),
        end = Offset(translateAnim.value + 200f, 0f)
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(brush = brush, shape = RoundedCornerShape(size = 100.dp))
    )
}

@Composable
fun PageLoader(modifier: Modifier = Modifier) {
    Column {
        Column(
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
        ) {
            CustomLoadingSpinner()
        }
        Spacer(modifier = Modifier.height(12.dp))
        ShimmerEffect(
            modifier = Modifier
                .height(18.dp)
                .width(200.dp)
                .padding(end = 20.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))
        ShimmerEffect(
            modifier = Modifier
                .fillMaxWidth()
                .height(18.dp)
                .padding(end = 20.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))
        ShimmerEffect(
            modifier = Modifier
                .fillMaxWidth()
                .height(18.dp)
                .padding(end = 20.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))
    }
    Spacer(modifier = Modifier.height(12.dp))
}