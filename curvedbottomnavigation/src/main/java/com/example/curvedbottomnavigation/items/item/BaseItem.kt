package com.example.curvedbottomnavigation.items.item

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight

/**
 * BaseItem represents a data class that contains common
 * @property iconColor icon color
 * @property selectedIconColor selected item color and if it is not initialized it takes iconColor
 * @property icon this icon will be displayed when item is not selected
 * @property selectedIcon this icon will be displayed when item is selected and if it is not initialized it takes icon
 * @property onItemSelected is a call back for current item
 * @property iconSize the size of icon
 * @property animDurationMillis duration for displaying animation
 * @property radius the radius of circular animation
 * @property textColor the color of text
 * @property textStyle
 * @property textFontWeight
 * @property textFontStyle
 * @property textFontFamily
 */

sealed class BaseItem(
    open val iconColor: Color,
    open val selectedIconColor: Color?,
    open val icon: ImageVector?,
    open val text: String?,
    open val selectedIcon: ImageVector?,
    open val onItemSelected: () -> Unit = {},
    open val iconSize: Float,
    open val animDurationMillis: Int,
    open val radius: Float,
    open val textColor: Color?,
    open val textStyle: TextStyle,
    open val textFontWeight: FontWeight,
    open val textFontStyle: FontStyle,
    open val textFontFamily: FontFamily,
)
