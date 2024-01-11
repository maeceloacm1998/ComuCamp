package comunexo.feature.game.model

import kotlinx.serialization.Serializable

@Serializable
data class OptionItem(
    val title: String = "",
    val category: String = "",
    val color: String = "",
    var isChecked: Boolean = false,
    var isError: Boolean = false,
)