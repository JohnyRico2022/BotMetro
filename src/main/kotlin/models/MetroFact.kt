package models

data class MetroFact(
    val id: Int,
    val text: String,
    val type: FactType,                                  // О станции или общем метро
    val relatedStationId: Int? = null,                   // Если факт о конкретной станции
    val category: FactCategory = FactCategory.GENERAL    // Для фильтрации
) {
    enum class FactType {
        STATION_FACT,            // Факт о конкретной станции
        METRO_GENERAL_FACT       // Общий факт о метро
    }

    enum class FactCategory {
        GENERAL,             // Общие факты
        HISTORICAL,          // Исторические
        TECHNICAL,           // Технические
        ARCHITECTURE,        // Архитектурные
        RECORDS              // Рекорды и достижения
    }
}