package models

data class MetroStation(
    val id: Int,
    val name: String,
    val lineId: Int,
    val neighbors: Map<Int, Int> = emptyMap(),
    val transferTo: List<Int>? = null,
    val openingYear: Int,
    val depth: Int
)