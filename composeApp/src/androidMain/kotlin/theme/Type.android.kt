package theme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import org.app.project.R

actual val robotoFontFamily: FontFamily
    get() = FontFamily(
        Font(R.font.roboto_regular),
        Font(R.font.roboto_medium),
        Font(R.font.roboto_bold)
    )