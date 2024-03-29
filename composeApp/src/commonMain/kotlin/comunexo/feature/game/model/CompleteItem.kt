package comunexo.feature.game.model

import kotlinx.serialization.Serializable

@Serializable
data class CompleteItem(
    val title: String = "",
    val options: String = "",
    val color: String = ""
)