package comunexo.feature.game

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import comunexo.feature.game.model.CompleteItem
import comunexo.feature.game.model.Game
import comunexo.feature.game.model.GameOption
import comunexo.feature.game.model.OptionItem
import core.firebase.FirebaseClient
import core.firebase.FirebaseConstants
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GameScreenModel(
    private val client: FirebaseClient
) : ScreenModel {
    private val viewModelState = MutableStateFlow(GameScreenModelState())

    private var id: String = ""
    private var options: MutableList<OptionItem> = mutableListOf()
    private var optionsSelected: MutableList<OptionItem> = mutableListOf()
    private var completeItemsList: MutableList<CompleteItem> = mutableListOf()
    private var tryCount: Int = 0

    val uiState = viewModelState
        .map(GameScreenModelState::toUiState)
        .stateIn(
            screenModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )

    fun initGame(gameOption: GameOption) {
        screenModelScope.launch {
            client.getSpecificDocument(
                collectionPath = FirebaseConstants.Collections.CONEXALVO_GAME,
                documentPath = gameOption.id
            ).onSuccess {
                try {
                    val game = it.data<Game>()
                    handleGame(game)
                } catch (_: IllegalArgumentException) {
                    createGame(gameOption)
                }
            }.onFailure {
                val y = ""
            }
        }
    }

    private suspend fun createGame(gameOption: GameOption) {
        val game = Game(
            id = gameOption.id,
            tryCount = 0,
            optionItems = gameOption.optionItem,
            completeItems = mutableListOf()
        )
        client.setSpecificDocument(FirebaseConstants.Collections.CONEXALVO_GAME, game.id, game)
        handleGame(game)
    }

    private fun handleGame(game: Game) {
        completeItemsList = game.completeItems.toMutableList()
        options = game.optionItems.toMutableList()
        id = game.id

        onRefreshGame(game)
        onClearOptionsSelected()
    }


    fun onCheckOption(optionSelected: OptionItem) {
        updateOptionSelectedList(optionSelected)
        updateCheckedOptionUi(optionSelected)
        if (optionsSelected.size == 4) {
            if (checkOptionsHaveSameCategory()) {
                onCompleteItem(optionSelected)
            } else {
                handleOptionsNotHaveSameCategory()
            }
        }
    }

    private fun onCompleteItem(optionSelected: OptionItem) {
        val completeItem = CompleteItem(
            title = optionSelected.category,
            options = optionsSelected.map { it.title }.toString(),
            color = optionSelected.color
        )
        completeItemsList.add(completeItem)
        optionsSelected.forEach { item ->
            options.remove(item)
        }

        val game = Game(
            tryCount = tryCount,
            optionItems = options,
            completeItems = completeItemsList
        )

        onClearOptionsSelected()
        onRefreshGame(game)
        onUpdateGame(game)
    }

    private fun updateOptionSelectedList(option: OptionItem) {
        if (optionsSelected.contains(option)) {
            optionsSelected.remove(option)
        } else {
            optionsSelected.add(option)
        }
    }

    private fun updateCheckedOptionUi(optionSelected: OptionItem) {
        options.forEach { originalItem ->
            if (originalItem == optionSelected) {
                originalItem.isChecked = !originalItem.isChecked
            }
        }
        onRefreshGame(
            Game(
                tryCount = tryCount,
                optionItems = options,
                completeItems = completeItemsList
            )
        )
    }

    private fun handleOptionsNotHaveSameCategory() {
        screenModelScope.launch {
            onErrorOptions()
            delay(800L)
            onClearOptions()
            onAddTryCount()
            onClearOptionsSelected()
        }
    }

    private fun checkOptionsHaveSameCategory(): Boolean {
        return optionsSelected.map { it.category }.toSet().size == 1
    }

    private fun onErrorOptions() {
        val optionsWithError = options
        optionsWithError.forEach { originalItem ->
            if (optionsSelected.contains(originalItem)) {
                originalItem.run {
                    isError = true
                    isChecked = false
                }
            }
        }
        onRefreshGame(
            Game(
                tryCount = tryCount,
                optionItems = optionsWithError,
                completeItems = completeItemsList
            )
        )
    }

    private fun onClearOptions() {
        val clearOptions = options
        clearOptions.forEach { originalItem ->
            if (optionsSelected.contains(originalItem)) {
                originalItem.run {
                    isError = false
                    isChecked = false
                }
            }
        }
        onRefreshGame(
            Game(
                tryCount = tryCount,
                optionItems = clearOptions,
                completeItems = completeItemsList
            )
        )
    }

    private fun onClearOptionsSelected() {
        optionsSelected = mutableListOf()
    }

    private fun onAddTryCount() {
        val game = Game(
            tryCount = tryCount + 1,
            optionItems = options,
            completeItems = completeItemsList
        )
        tryCount = game.tryCount
        onUpdateGame(game)
        onRefreshGame(game)
    }

    private fun onRefreshGame(
        game: Game,
        delay: Long = 2L
    ) {
        screenModelScope.launch {
            viewModelState.update {
                it.copy(onLoading = true)
            }
            delay(delay)
            viewModelState.update {
                it.copy(
                    onLoading = false,
                    tryCount = game.tryCount,
                    options = game.optionItems,
                    completeItems = game.completeItems
                )
            }
        }
    }

    private fun onUpdateGame(game: Game) {
        screenModelScope.launch {
            client.setSpecificDocument(
                collectionPath = FirebaseConstants.Collections.CONEXALVO_GAME,
                documentPath = id,
                data = game
            )
        }
    }
}