package com.example.curvedbottomnavigation.items.ui


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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import com.example.curvedbottomnavigation.items.item.BaseItem
import com.example.curvedbottomnavigation.items.item.CircularItem
import com.example.curvedbottomnavigation.items.item.LinearItem
import kotlinx.coroutines.launch
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

/**
 * @param isActive shows if the item is selected or not
 * @param item base item that sets the animations
 * @param onClick on item click callback
 */
@SuppressLint("UnrememberedAnimatable", "CoroutineCreationDuringComposition")
@Composable
fun ItemUI(
    isActive: Boolean,
    item: BaseItem,
    onClick: () -> Unit
) {
    val animatedAlpha = Animatable(0f)
    val vectorPainter = item.selectedIcon?.let { rememberVectorPainter(image = it) }
    val lineVectorPainter = item.icon?.let { rememberVectorPainter(image = it) }

    rememberCoroutineScope().launch {
        animatedAlpha.animateTo(
            100f,
            animationSpec = tween(
                item.animDurationMillis
            )
        )
    }

    Canvas(modifier = Modifier
        .height(50.dp)
        .width(50.dp)
        .clickable {
            onClick()
        }) {


        val centerX = size.width / 2
        val centerY = size.height / 2
        val leftX = centerX - item.radius / 2
        val topY = centerY - item.radius / 2
        val myValue = animatedAlpha.value * 360f / 100f
        val iconAlpha = animatedAlpha.value / 100f

        translate(
            top = centerY - item.iconSize / 2,
            left = centerX - item.iconSize / 2
        ) {
            if (isActive) {
                vectorPainter?.let {
                    with(vectorPainter) {
                        draw(
                            Size(item.iconSize, item.iconSize),
                            colorFilter = if (isActive) ColorFilter.tint(
                                item.selectedIconColor ?: item.iconColor
                            ) else ColorFilter.tint(
                                item.iconColor
                            ),
                            alpha = iconAlpha
                        )
                    }
                }
            }

            lineVectorPainter?.let {
                with(lineVectorPainter) {
                    draw(
                        Size(item.iconSize, item.iconSize),
                        colorFilter = if (isActive) ColorFilter.tint(
                            item.selectedIconColor ?: item.iconColor
                        ) else ColorFilter.tint(
                            item.iconColor
                        ),
                    )
                }
            }
        }


        //draw Linear
        if (item is LinearItem) {
            val sweepAngle =
                if (myValue < 180) myValue * myValue * (1f / 180) else (myValue - 360) * (myValue - 360) * (-1f / 180) + 360
            if (isActive) {
                drawCircle(
                    color = item.circularAnimColor ?: item.iconColor,
                    radius = item.stroke / 2,
                    center = Offset(
                        ((item.radius / 2) * cos(Math.toRadians((sweepAngle + myValue + 90).toDouble()))).toFloat() + leftX + item.radius / 2,
                        ((item.radius / 2) * sin(Math.toRadians((sweepAngle + myValue + 90).toDouble()))).toFloat() + topY + item.radius / 2
                    )
                )
            }
            if (myValue < 360f) {
                if (isActive) {
                    drawArc(
                        style = Stroke(
                            width = item.stroke - item.stroke * myValue / 360f,
                            cap = StrokeCap.Round
                        ),
                        color = item.circularAnimColor ?: item.iconColor,
                        startAngle = myValue + 90,
                        sweepAngle = sweepAngle,
                        topLeft = Offset(leftX, topY),
                        useCenter = false,
                        size = Size(item.radius, item.radius),
                    )
                }
            }
        }
        //draw circular
        else if (item is CircularItem) {

            if (isActive) {
                val radius = item.radius
                val myList = ArrayList<Float>()

                for (i in 0 until item.circlesNumber) {
                    if (i < item.circlesNumber / 2) {
                        myList.add(item.circlesMaxRadius * i / item.circlesNumber)
                    } else {
                        myList.add(item.circlesMaxRadius - item.circlesMaxRadius * i / item.circlesNumber)
                    }
                }

                for ((index, r) in myList.withIndex()) {
                    drawCircle(
                        color = item.circlesColor ?: item.iconColor,
                        radius = (animatedAlpha.value / 100) * r,
                        center = Offset(
                            x = (radius * cos(
                                ((animatedAlpha.value / 100) * index * 2 * PI / item.circlesNumber) + PI / 2
                            )).toFloat() + leftX + item.radius / 2, y = (radius * sin(
                                ((animatedAlpha.value / 100) * index * 2 * PI / item.circlesNumber) + PI / 2
                            )).toFloat() + topY + item.radius / 2
                        )
                    )
                }
            }
        }
    }
}
