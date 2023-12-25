package components.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import theme.CustomDimensions
import theme.GrayLight
import theme.Primary


@Composable
fun OutlinedButtonCustom(
    title: String,
    titleColor: Color? = null,
    containerColor: Color? = null,
    loading: Boolean = false,
    modifier: Modifier = Modifier,
    onButtonListener: () -> Unit,
) {
    OutlinedButton(
        modifier = modifier
            .fillMaxWidth()
            .height(CustomDimensions.padding50),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Color.White,
            disabledContainerColor = if (!loading) GrayLight else Color.White,
            contentColor = Primary
        ),
        border = BorderStroke(
            width = CustomDimensions.padding1,
            color = Primary
        ),
        enabled = !loading,
        onClick = onButtonListener,
    ) {
        if (loading) {
            CircularProgressIndicator(
                color = Primary, modifier = Modifier.size(
                    width = CustomDimensions.padding30,
                    height = CustomDimensions.padding30
                )
            )
        } else {
            Text(
                text = title,
                color = Primary,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}