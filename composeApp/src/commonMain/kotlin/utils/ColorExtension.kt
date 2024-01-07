package utils

import androidx.compose.ui.graphics.Color

fun String.toColor(): Color {
    val r = substring(1..2).toInt(16)
    val g = substring(3..4).toInt(16)
    val b = substring(5..6).toInt(16)
    return Color(r, g, b)
}