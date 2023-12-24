package theme

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.platform.Typeface
import org.jetbrains.skia.FontStyle
import org.jetbrains.skia.Typeface

private fun loadCustomFont(name: String): Typeface {
    return Typeface.makeFromName(name, FontStyle.NORMAL)
}

actual val robotoFontFamily: FontFamily
    get() = FontFamily(
        Typeface(loadCustomFont("roboto"))
    )