package com.example.curvedbottomnavigation

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.curvedbottomnavigation.items.item.BaseItem
import com.example.curvedbottomnavigation.items.ui.ItemUI

/**
 * CurvedBottomNavigation is a composable contains items and background
 * background curve is changeable
 * @param items is a list of items that can be LinearItem or CircularItem
 * @param color is backGround color of CurvedBottomNavigation
 * @param hills is number of hills
 * @param height is the height of CurvedBottomNavigation background
 * @param topPadding is top padding
 * @param verticalControl is to control curve (positive for convex and negative for concave)
 * @param space is the space between the text and icon
 * @param onItemClick is listener of whole bottom navigation
 */

@Composable
fun CurvedBottomNavigation(
    items: List<BaseItem>,
    color: Color,
    defaultItemIndex: Int? = null,
    hills: Int = 2,
    height: Dp = 100.dp,
    topPadding: Dp = 0.dp,
    verticalControl: Float = -30f,
    space:Dp =3.dp,
    onItemClick: (index: Int) -> Unit
) {
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
                val py = if (i % 2 == 0) 0f else verticalControl
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
            //draw items
            val isActiveList = remember {
                mutableStateListOf(
                    *MutableList(items.size) {
                        false
                    }.toTypedArray()
                )
            }
            LaunchedEffect(Unit) {
                defaultItemIndex?.let {
                    isActiveList[it] = true
                }
            }
            for ((i, item) in items.withIndex()) {
                Column (horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(space = space)){
                    ItemUI(
                        item = item,
                        isActive = isActiveList[i],
                    ) {
                        for (j in 0 until isActiveList.size)
                            isActiveList[j] = false
                        isActiveList[i] = true
                        onItemClick(i)
                        item.onItemSelected
                    }
                    item.text?.let {
                        Text(
                            text = it,
                            color = item.textColor ?: item.iconColor,
                            style = item.textStyle,
                            fontWeight = item.textFontWeight,
                            fontStyle = item.textFontStyle,
                            fontFamily = item.textFontFamily
                        )
                    }
                }
            }
        }
    }
}
