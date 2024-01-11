package comunexo.feature.game.model

import kotlinx.serialization.Serializable

@Serializable
data class Game(
    val id: String = "",
    val tryCount: Int = 0,
    val optionItems: List<OptionItem> = mutableListOf(),
    val completeItems: List<CompleteItem> = mutableListOf()
)