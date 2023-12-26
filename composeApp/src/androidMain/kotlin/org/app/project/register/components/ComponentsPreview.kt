package org.app.project.register.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import components.button.ButtonCustom
import components.button.OutlinedButtonCustom
import components.textfield.TextFieldCustom

@Preview
@Composable
fun TextInputPreview() {
    TextFieldCustom(label = "Nome de aventureiro(a)", onChangeListener = {})
}

@Preview
@Composable
fun OutlinedButtonPreview() {
    OutlinedButtonCustom(title = "Teste", onButtonListener = {})
}

@Preview
@Composable
fun ButtonCustomPreview() {
    ButtonCustom(title = "Teste", onButtonListener = {})
}