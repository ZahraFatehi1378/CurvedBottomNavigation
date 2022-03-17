package com.example.curvedbottomnavigation.items.item

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight

/**
 * {@inheritDoc}
 * @property circularAnimColor the color of circular line for animation
 * @property stroke the stroke of circular line
 */
data class LinearItem(
    override val iconColor: Color = Color(0xFFFFFFFF),
    override val selectedIconColor: Color? = null,
    override val textColor: Color? = null,
    override val icon: ImageVector? = null,
    override val text: String? = null,
    override val selectedIcon: ImageVector? = null,
    override val onItemSelected: () -> Unit = {},
    override val iconSize: Float = 70f,
    override val animDurationMillis: Int = 1200,
    override val textStyle: TextStyle = TextStyle.Default,
    override val textFontWeight: FontWeight = FontWeight.Normal,
    override val textFontStyle: FontStyle = FontStyle.Normal,
    override val textFontFamily: FontFamily = FontFamily.Default,
    override val radius: Float = 120f,
    val circularAnimColor: Color? = null,
    val stroke: Float = 15f
) : BaseItem(
    iconColor = iconColor,
    selectedIconColor = selectedIconColor,
    textColor = textColor,
    icon = icon,
    text = text,
    selectedIcon = selectedIcon,
    onItemSelected = onItemSelected,
    iconSize = iconSize,
    animDurationMillis = animDurationMillis,
    radius = radius,
    textFontFamily = textFontFamily,
    textFontStyle = textFontStyle,
    textFontWeight = textFontWeight,
    textStyle = textStyle
)