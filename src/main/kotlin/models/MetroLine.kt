package models

data class MetroLine(
    val id: Int,
    val name: String,
    val color: String,
    val stationIds: List<Int>,
    val connectedLineIds: List<Int> = emptyList()
)
