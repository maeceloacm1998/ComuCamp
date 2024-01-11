package comunexo.feature.game.model

import kotlinx.serialization.Serializable

@Serializable
data class GameOption(
    val id: String = "",
    val title: String = "",
    val optionItem: List<OptionItem> = mutableListOf()
)
