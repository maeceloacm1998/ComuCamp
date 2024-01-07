package org.app.project.comunexo.feature.game

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import comunexo.feature.game.GameScreen
import comunexo.feature.game.GameScreenModelUiState
import comunexo.feature.game.model.OptionItem

@Preview
@Composable
fun GameRoutePreview() {
    val options = listOf(
        OptionItem(
            title = "Item 1",
            category = "Jesus"
        ),
        OptionItem(
            title = "Item 2",
            category = "Jesus"
        ),
        OptionItem(
            title = "Item 3",
            category = "Jesus"
        ),
        OptionItem(
            title = "Item 4",
            category = "Jesus"
        ),
        OptionItem(
            title = "Item 5",
            category = "Cloro"
        ),
        OptionItem(
            title = "Item 6",
            category = "Cloro"
        ),
        OptionItem(
            title = "Item 7",
            category = "Cloro"
        ),
        OptionItem(
            title = "Item 8",
            category = "Cloro"
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