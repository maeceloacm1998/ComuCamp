package comunexo.feature.game

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import comunexo.feature.game.GameScreenType.Game
import comunexo.feature.game.GameScreenType.GameFinish
import comunexo.feature.game.model.OptionItem

class GameRoute : Screen {
    @Composable
    override fun Content() {
        val screenModel = getScreenModel<GameScreenModel>()
        val uiState by screenModel.uiState.collectAsState()

        GameRoute(
            uiState = uiState,
            onCheckedItem = { screenModel.onCheckOption(it) }
        )
    }

    @Composable
    fun GameRoute(
        uiState: GameScreenModelUiState,
        onCheckedItem: (option: OptionItem) -> Unit
    ) {
        when (getGameScreenType(uiState)) {
            Game -> {
                check(uiState is GameScreenModelUiState.GameState)
                GameScreen(
                    uiState = uiState,
                    onCheckedItem = onCheckedItem
                )
            }

            GameFinish -> {
                check(uiState is GameScreenModelUiState.GameFinishState)
            }
        }
    }
}

private enum class GameScreenType {
    Game,
    GameFinish
}

@Composable
private fun getGameScreenType(
    uiState: GameScreenModelUiState
): GameScreenType = when (uiState) {
    is GameScreenModelUiState.GameState -> Game
    is GameScreenModelUiState.GameFinishState -> GameFinish
}