package comunexo.feature.game.model

data class OptionItem(
    val title: String,
    val category: String,
    var isChecked: Boolean = false,
    var isError: Boolean = false
)