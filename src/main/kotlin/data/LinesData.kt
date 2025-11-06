package data

import models.MetroLine

object LinesData {

    val lines = listOf(
        MetroLine(
            id = 1,
            name = "Кировско-Выборгская",
            color = "\uD83D\uDD34",
            stationIds = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19),
            connectedLineIds = listOf(2, 3, 4)
        ),
        MetroLine(
            id = 2,
            name = "Московско-Петроградская",
            color = "\uD83D\uDD35",
            stationIds = listOf(20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34),
            connectedLineIds = listOf(1, 3, 5)
        ),
        MetroLine(
            id = 3,
            name = "Невско-Василеостровская",
            color = "\uD83D\uDFE2",
            stationIds = listOf(35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49),
            connectedLineIds = listOf(1, 2, 4, 5)
        ),
        MetroLine(
            id = 4,
            name = "Лахтинско-Правобережная",
            color = "\uD83D\uDFE0",
            stationIds = listOf(50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61),
            connectedLineIds = listOf(1, 3, 5)
        ),
        MetroLine(
            id = 5,
            name = "Фрунзенско-Приморская",
            color = "\uD83D\uDFE3",
            stationIds = listOf(62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76),
            connectedLineIds = listOf(2, 3, 4)
        ),
        /*MetroLine(
            id = 6,
            name = "Красносельско-Калининская",
            color = "\uD83D\uDFE4",
            stationIds = listOf(77, 78),
            connectedLineIds = listOf(5) // пока только с 5-й линией
        )*/
    )

    // Кэш для быстрого поиска
    private val linesCache = mutableMapOf<Int, MetroLine>()

    // Инициализация кэша
    private fun initializeCache() {
        if (linesCache.isEmpty()) {
            lines.forEach { line ->
                linesCache[line.id] = line
            }
        }
    }

    fun findLineById(lineId: Int): MetroLine? {
        initializeCache()
        return linesCache[lineId]
    }

    fun getEmojiNumber(lineNumber: Int): String {
        return when (lineNumber) {
            1 -> "1\uFE0F⃣"
            2 -> "2\uFE0F⃣"
            3 -> "3\uFE0F⃣"
            4 -> "4\uFE0F⃣"
            5 -> "5\uFE0F⃣"
            else -> "\uD83D\uDD22"
        }
    }
}