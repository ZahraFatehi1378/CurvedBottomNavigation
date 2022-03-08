package com.example.curvedbottomnavigation


import android.annotation.SuppressLint
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import kotlin.math.cos
import kotlin.math.sin

@SuppressLint("UnrememberedAnimatable", "CoroutineCreationDuringComposition")
@Composable
fun BottomItem(
    iconSize: Float, selectedIcon: ImageVector, defaultIcon: ImageVector,
    selectedItemColor: Color,
    itemColor: Color,
    circularAnimColor: Color? = null,
    isActive: Boolean,
    animDurationMillis: Int = 1200,
    radius: Float,
    circleStroke: Float,
    onClick: () -> Unit
) {
    val animatedAlpha = Animatable(0f)

    rememberCoroutineScope().launch {
        animatedAlpha.animateTo(
            100f,
            animationSpec = tween(
                animDurationMillis
            )
        )
    }

    val imagePainter = rememberVectorPainter(image = selectedIcon)
    val lineImagePainter = rememberVectorPainter(image = defaultIcon)

    Canvas(modifier = Modifier
        .height(50.dp)
        .width(50.dp)
        .clickable {
            onClick()
        }) {

        val centerX = size.width / 2
        val centerY = size.height / 2
        val leftX = centerX - radius / 2
        val topY = centerY - radius / 2
        val myValue = animatedAlpha.value * 360f / 100f
        val iconAlpha = animatedAlpha.value / 100f

        translate(
            top = centerY - iconSize / 2,
            left = centerX - iconSize / 2
        ) {
            if (isActive) {
                with(imagePainter) {
                    draw(
                        Size(iconSize, iconSize),
                        colorFilter = if (isActive) ColorFilter.tint(selectedItemColor) else ColorFilter.tint(
                            itemColor
                        ),
                        alpha = iconAlpha
                    )
                }
            }
            with(lineImagePainter) {
                draw(
                    Size(iconSize, iconSize),
                    colorFilter = if (isActive) ColorFilter.tint(selectedItemColor) else ColorFilter.tint(
                        itemColor
                    ),
                )
            }
        }

        val sweepAngle =
            if (myValue < 180) myValue * myValue * (1f / 180) else (myValue - 360) * (myValue - 360) * (-1f / 180) + 360
        if (isActive) {
            drawCircle(
                color = circularAnimColor ?: itemColor,
                radius = circleStroke / 2,
                center = Offset(
                    ((radius / 2) * cos(Math.toRadians((sweepAngle + myValue + 90).toDouble()))).toFloat() + leftX + radius / 2,
                    ((radius / 2) * sin(Math.toRadians((sweepAngle + myValue + 90).toDouble()))).toFloat() + topY + radius / 2
                )
            )
        }
        if (myValue < 360f) {
            if (isActive) {
                drawArc(
                    style = Stroke(width = circleStroke - circleStroke * myValue / 360f, cap = StrokeCap.Round),
                    color = circularAnimColor ?: itemColor,
                    startAngle = myValue + 90,
                    sweepAngle = sweepAngle,
                    topLeft = Offset(leftX, topY),
                    useCenter = false,
                    size = Size(radius, radius),
                )
            }
        }
    }
}
