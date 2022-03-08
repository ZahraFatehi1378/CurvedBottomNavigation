package com.example.curvedbottomnavigation

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.MutableStateFlow


class BottomNavItem(
    val itemColor: Color = Color(0xFFFFFFFF),
    val selectedItemColor: Color? = null,
    val circularAnimColor: Color? = null,
    val icon: ImageVector,
    val selectedIcon: ImageVector? = null,
    val onItemSelected: () -> Unit = {},
    val iconSize: Float = 70f,
    val radius: Float = 120f,
    val stroke: Float = 15f
)

@Composable
fun CurvedBottomNavigation2(
    items: List<BottomNavItem>,
    color: Color,
    defaultItemIndex: Int? = null,
    hills: Int = 2,
    height: Dp = 80.dp,
    topPadding: Dp = 0.dp,
    verticalControl: Float = 20f,
    onItemClick: (index: Int) -> Unit
) {
    val isFirstRunning = (MutableStateFlow(true))

    Box {
        val path = remember { Path() }
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(height)
                .padding(top = topPadding)
        ) {
            path.moveTo(0f, 0f)
            for (i in 1..2 * (hills - 1)) {
                val px = (i.toFloat() / (2 * (hills - 1))) * size.width
                var py = if (i % 2 == 0) 0f else verticalControl
                val y1 =
                    if (i % 2 == 1) (verticalControl) / hills.toFloat() else verticalControl
                val y2 =
                    if (i % 2 == 1) verticalControl else verticalControl / hills.toFloat()
                val x1 = ((i - 1) + 1.toFloat() / 4) / (2 * (hills - 1)) * size.width
                val x2 = ((i - 1) + 3.toFloat() / 4) / (2 * (hills - 1)) * size.width
                path.cubicTo(x1, y1, x2, y2, px, py)
            }
            path.lineTo(size.width, size.height)
            path.lineTo(0f, size.height)
            path.close()

            drawPath(path = path, color = color, style = Fill)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 18.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {


            val isActiveList = remember {
                mutableStateListOf(
                    *MutableList(items.size) {
                        //it == defaultItemIndex ?: 100
                        false
                    }.toTypedArray()
                )
            }

            LaunchedEffect(null) {
                defaultItemIndex?.let {
                    isActiveList[it] = true
                }
            }

            for ((i, item) in items.withIndex()) {
                BottomItem(
                    iconSize = item.iconSize,
                    selectedIcon = item.selectedIcon ?: item.icon,
                    defaultIcon = item.icon,
                    itemColor = item.itemColor,
                    selectedItemColor = item.selectedItemColor ?: item.itemColor,
                    animDurationMillis = 1200,
                    isActive = isActiveList[i],
                    circularAnimColor = item.circularAnimColor,
                    radius = item.radius,
                    circleStroke = item.stroke
                ) {
                    for (j in 0 until isActiveList.size)
                        isActiveList[j] = false
                    isActiveList[i] = true
                    onItemClick(i)
                    item.onItemSelected
                }
            }
        }
    }
}
