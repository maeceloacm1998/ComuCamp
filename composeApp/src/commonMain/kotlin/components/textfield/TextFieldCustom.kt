package components.textfield

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import theme.Error
import theme.GrayDark
import theme.Primary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldCustom(
    modifier: Modifier = Modifier,
    label: String,
    value: String = "",
    placeholder: String = "",
    supportText: String = "",
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Go,
    keyboardActions: KeyboardActions? = null,
    isPasswordToggle: Boolean = false,
    maxLines: Int = 1,
    maxLength: Int = 100,
    onChangeListener: (text: String) -> Unit,
    endIconImageVector: ImageVector? = null,
    endIconDescription: String = "",
    endIconListener: (() -> Unit)? = null,
    error: Boolean = false
) {
    var text by rememberSaveable { mutableStateOf(value) }
    var passwordHidden by rememberSaveable { mutableStateOf(isPasswordToggle) }

    OutlinedTextField(
        value = text,
        onValueChange = {
            if (it.length <= maxLength) {
                text = it
                onChangeListener(it)
            }
        },
        modifier,
        label = {
            Text(
                text = label, color = if (error) MaterialTheme.colorScheme.error else GrayDark
            )
        },
        placeholder = { Text(placeholder, color = GrayDark) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Primary, errorLabelColor = Error,
            containerColor = Color.White,
            unfocusedBorderColor = GrayDark,
            focusedTextColor = GrayDark
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = keyboardType, imeAction = imeAction
        ),
        keyboardActions = keyboardActions ?: KeyboardActions { },
        visualTransformation = if (passwordHidden) PasswordVisualTransformation() else VisualTransformation.None,
        maxLines = maxLines,
        isError = error,
        supportingText = {
            Text(
                supportText, color = if (error) Error else GrayDark
            )
        },
        trailingIcon = {
            if (isPasswordToggle) {
                IconButton(onClick = { passwordHidden = !passwordHidden }) {
                    val visibilityIcon =
                        if (passwordHidden) Icons.Filled.Visibility else Icons.Outlined.VisibilityOff
                    val description = if (passwordHidden) "Show password" else "Hide password"
                    Icon(
                        imageVector = visibilityIcon,
                        contentDescription = description,
                        tint = if (error) Error else Primary
                    )
                }
            } else {
                IconButton(onClick = { endIconListener?.let { it() } }) {
                    endIconImageVector?.let {
                        Icon(
                            imageVector = it,
                            contentDescription = endIconDescription,
                            tint = if (error) Error else Primary
                        )
                    }
                }
            }
        },
    )
}
