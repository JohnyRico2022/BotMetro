package models

data class Route(
    var path: MutableList<MetroStation>,
    val totalTime: Int,
    val transfer: Int,
)
