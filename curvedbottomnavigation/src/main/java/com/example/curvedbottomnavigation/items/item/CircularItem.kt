package com.example.curvedbottomnavigation.items.item

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight

/**
 * {@inheritDoc}
 * @property circlesColor circles color
 * @property circlesMaxRadius max circle radius
 * @property circlesNumber number of circles
 */

data class CircularItem(
    override val iconColor: Color = Color(0xFFFFFFFF),
    override val selectedIconColor: Color? = null,
    override val textColor: Color? = null,
    override val icon: ImageVector? = null,
    override val text: String? = null,
    override val selectedIcon: ImageVector? = null,
    override val onItemSelected: () -> Unit = {},
    override val iconSize: Float = 80f,
    override val animDurationMillis: Int = 500,
    override val textStyle: TextStyle = TextStyle.Default,
    override val textFontWeight: FontWeight = FontWeight.Normal,
    override val textFontStyle: FontStyle = FontStyle.Normal,
    override val textFontFamily: FontFamily = FontFamily.Default,
    override val radius: Float = 70f,
    val circlesColor: Color? = null,
    val circlesMaxRadius: Float = 12f,
    val circlesNumber: Int = 15
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