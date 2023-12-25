package theme

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

expect val robotoFontFamily: FontFamily

val typography: Typography
    get() = Typography(
        titleLarge = TextStyle(
            fontFamily = robotoFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp
        ),
        titleMedium = TextStyle(
            fontFamily = robotoFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        ),
        titleSmall = TextStyle(
            fontFamily = robotoFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp
        ),
        labelLarge = TextStyle(
            fontFamily = robotoFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp
        ),
        labelMedium = TextStyle(
            fontFamily = robotoFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp
        ),
        labelSmall = TextStyle(
            fontFamily = robotoFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp
        ),
        bodyLarge = TextStyle(
            fontFamily = robotoFontFamily,
            fontWeight = FontWeight.Light,
            fontSize = 14.sp
        ),
        bodyMedium = TextStyle(
            fontFamily = robotoFontFamily,
            fontWeight = FontWeight.Light,
            fontSize = 12.sp
        ),
        displayLarge = TextStyle(
            fontFamily = robotoFontFamily,
            fontWeight = FontWeight.Light,
            fontSize = 20.sp
        ),
    )
