package comunexo.feature.game

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import comunexo.feature.game.model.OptionItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GameScreenModel : ScreenModel {
    private val viewModelState = MutableStateFlow(GameScreenModelState())
    private var options: MutableList<OptionItem> = mutableListOf()
    private var optionsSelected: MutableList<OptionItem> = mutableListOf()


    val uiState = viewModelState
        .map(GameScreenModelState::toUiState)
        .stateIn(
            screenModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )

    init {
        fetchOptions()
    }

    fun fetchOptions() {
        val optionsFake = listOf(
            OptionItem(
                title = "Item 1",
                category = "Jesus"
            ),
            OptionItem(
                title = "Hipotese",
                category = "Jesus"
            ),
            OptionItem(
                title = "Hospedagem",
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
                title = "Passaporte",
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

        options = optionsFake.toMutableList()
        viewModelState.update {
            it.copy(options = optionsFake.toMutableList())
        }
    }

    fun onCheckOption(optionSelected: OptionItem) {
        updateOptionSelectedList(optionSelected)
        updateCheckedOptionUi(optionSelected)
        if (optionsSelected.size == 4) {
            if (checkOptionsHaveSameCategory()) {

            } else {
                handleOptionsNotHaveSameCategory()
            }
        }
    }

    private fun updateOptionSelectedList(option: OptionItem) {
        if(optionsSelected.contains(option)) {
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
        onRefreshGame(options)
    }

    private fun handleOptionsNotHaveSameCategory() {
        screenModelScope.launch {
            onErrorOptions()
            delay(800L)
            onClearOptions()
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
        onRefreshGame(optionsWithError)
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
        onRefreshGame(clearOptions)
    }

    private fun onClearOptionsSelected() {
        optionsSelected = mutableListOf()
    }

    private fun onRefreshGame(
        options: MutableList<OptionItem>,
        delay: Long = 2L
    ) {
        screenModelScope.launch {
            viewModelState.update {
                it.copy(onLoading = true)
            }
            delay(delay)
            viewModelState.update { it.copy(onLoading = false, options = options) }
        }
    }
}