package core.utils

import getPlatform

fun String.gerarIdRandom(): String {
    val timestamp = getPlatform()
    val randomPart = (0..9999).random()
    val id = "$timestamp-$randomPart"
    return id
}