package comunexo.feature.game

import comunexo.feature.game.model.OptionItem
import core.utils.ErrorMessage

sealed interface GameScreenModelUiState {
    val finishGame: Boolean
    val onLoading: Boolean
    val errorMessages: ErrorMessage?

    data class GameFinishState(
        override val errorMessages: ErrorMessage?,
        override val onLoading: Boolean,
        override val finishGame: Boolean,
    ) : GameScreenModelUiState

    data class GameState(
        val options: MutableList<OptionItem>,
        override val errorMessages: ErrorMessage?,
        override val onLoading: Boolean,
        override val finishGame: Boolean,
    ) : GameScreenModelUiState
}

data class GameScreenModelState(
    val finishGame: Boolean = false,
    val onLoading: Boolean = false,
    val options: MutableList<OptionItem> = mutableListOf(),
    val errorMessages: ErrorMessage? = null,
) {
    fun toUiState(): GameScreenModelUiState =
        if (finishGame) {
            GameScreenModelUiState.GameFinishState(
                errorMessages = errorMessages,
                finishGame = finishGame,
                onLoading = onLoading
            )
        } else {
            GameScreenModelUiState.GameState(
                options = options,
                errorMessages = errorMessages,
                finishGame = finishGame,
                onLoading = onLoading
            )
        }
}