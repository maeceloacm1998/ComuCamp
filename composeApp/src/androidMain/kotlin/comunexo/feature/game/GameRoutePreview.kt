package comunexo.feature.game

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import comunexo.feature.game.model.OptionItem

@Preview
@Composable
fun GameRoutePreview() {
    val options = listOf(
        OptionItem(
            title = "Item 1",
            category = "Jesus",
            color = "#28A745",
            isChecked = false,
            isError = false
        ),
        OptionItem(
            title = "Hipotese",
            category = "Jesus",
            color = "#28A745",
            isChecked = false,
            isError = false
        ),
        OptionItem(
            title = "Hospedagem",
            category = "Jesus",
            color = "#28A745",
            isChecked = false,
            isError = false
        ),
        OptionItem(
            title = "Item 4",
            category = "Jesus",
            color = "#28A745",
            isChecked = false,
            isError = false
        ),
        OptionItem(
            title = "Item 5",
            category = "Cloro",
            color = "#dc3545",
            isChecked = false,
            isError = false
        ),
        OptionItem(
            title = "Passaporte",
            category = "Cloro",
            color = "#dc3545",
            isChecked = false,
            isError = false
        ),
        OptionItem(
            title = "Item 7",
            category = "Cloro",
            color = "#dc3545",
            isChecked = false,
            isError = false
        ),
        OptionItem(
            title = "Item 8",
            category = "Cloro",
            color = "#dc3545",
            isChecked = false,
            isError = false
        ),
    )
    GameScreen(
        uiState = GameScreenModelUiState.GameState(
            options = options.toMutableList(),
            completeItems = mutableListOf(),
            errorMessages = null,
            finishGame = false,
            tryCount = 2,
            onLoading = false
        )
    ) {}
}