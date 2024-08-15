package com.lyscraft.apparel.compose.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Created by Yash Chaturvedi
 */
@Composable
fun CustomLoadingSpinner() {
    val infiniteTransition = rememberInfiniteTransition(label = "Shimmer")
    val animatedIndex by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 12f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1200, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = "Shimmer"
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE0E0E0), shape = RoundedCornerShape(20.dp))
    ) {
        Canvas(modifier = Modifier.size(40.dp)) {
            val strokeWidth = 4.dp.toPx()
            val segmentAngle = 30f
            val totalSegments = 12
            val radius = size.minDimension / 2 - strokeWidth / 2
            val centerGap = 14.dp.toPx()

            for (i in 0 until totalSegments) { // Exclude the last segment
                if (i != animatedIndex.toInt() % totalSegments) {
                    rotate(degrees = i * segmentAngle) {
                        drawLine(
                            color = Color(0xFF888888),
                            start = center.copy(y = center.y - centerGap),
                            end = center.copy(y = center.y - radius),
                            strokeWidth = strokeWidth,
                            cap = StrokeCap.Round
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun CircularProgressBarDemo() {
    CustomLoadingSpinner()
}
