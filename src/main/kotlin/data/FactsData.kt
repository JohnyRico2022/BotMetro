package data

import models.MetroFact

object FactsData {

    val allFacts = listOf(

        MetroFact(
            id = 1,
            text = "Петербургский метрополитен — самый глубокий в мире по средней глубине залегания станций (≈60-70 метров)",
            type = MetroFact.FactType.METRO_GENERAL_FACT,
            category = MetroFact.FactCategory.RECORDS
        ),
        MetroFact(
            id = 2,
            text = "Первая очередь метро открылась 15 ноября 1955 года с 8 станциями",
            type = MetroFact.FactType.METRO_GENERAL_FACT,
            category = MetroFact.FactCategory.HISTORICAL
        ),
        MetroFact(
            id = 3,
            text = "Самый длинный перегон: «Проспект Ветеранов» — «Ленинский проспект» (4,5 км)",
            type = MetroFact.FactType.METRO_GENERAL_FACT,
            category = MetroFact.FactCategory.TECHNICAL
        ),
        MetroFact(
            id = 4,
            text = "Самый короткий перегон: «Невский проспект» — «Гостиный двор» (400 метров)",
            type = MetroFact.FactType.METRO_GENERAL_FACT,
            category = MetroFact.FactCategory.TECHNICAL
        ),
        MetroFact(
            id = 5,
            text = "Общая длина всех линий метро составляет около 125 км",
            type = MetroFact.FactType.METRO_GENERAL_FACT,
            category = MetroFact.FactCategory.TECHNICAL
        ),
        MetroFact(
            id = 6,
            text = "В оформлении станций использовано 17 видов мрамора и гранита",
            type = MetroFact.FactType.METRO_GENERAL_FACT,
            category = MetroFact.FactCategory.ARCHITECTURE
        ),
        MetroFact(
            id = 7,
            text = "На станции «Автово» использовано стекло с хрустальным рисунком",
            type = MetroFact.FactType.METRO_GENERAL_FACT,
            category = MetroFact.FactCategory.ARCHITECTURE
        ),
        MetroFact(
            id = 8,
            text = "Во время холодной войны метро проектировалось как убежище",
            type = MetroFact.FactType.METRO_GENERAL_FACT,
            category = MetroFact.FactCategory.HISTORICAL
        ),
        MetroFact(
            id = 9,
            text = "Ежедневный пассажиропоток составляет около 2 миллионов человек",
            type = MetroFact.FactType.METRO_GENERAL_FACT,
            category = MetroFact.FactCategory.TECHNICAL
        ),
        MetroFact(
            id = 10,
            text = "В питерском метро 72 станции и 5 депо",
            type = MetroFact.FactType.METRO_GENERAL_FACT,
            category = MetroFact.FactCategory.TECHNICAL
        ),

        // Факты о конкретных станциях
        MetroFact(
            id = 11,
            text = "Станция «Адмиралтейская» — самая глубокая в Петербурге (86 метров)",
            type = MetroFact.FactType.STATION_FACT,
            relatedStationId = 65,
            category = MetroFact.FactCategory.RECORDS
        ),
        MetroFact(
            id = 12,
            text = "На станции «Автово» 46 колонн, но только 16 несут нагрузку",
            type = MetroFact.FactType.STATION_FACT,
            relatedStationId = 17,
            category = MetroFact.FactCategory.ARCHITECTURE
        ),
        MetroFact(
            id = 13,
            text = "«Девяткино» — единственная станция метро в Ленинградской области",
            type = MetroFact.FactType.STATION_FACT,
            relatedStationId = 1,
            category = MetroFact.FactCategory.RECORDS
        ),
        MetroFact(
            id = 14,
            text = "Станция «Адмиралтейская» носила это название с 1992 года, но открылась только в 2011",
            type = MetroFact.FactType.STATION_FACT,
            relatedStationId = 65,
            category = MetroFact.FactCategory.HISTORICAL
        ),
        MetroFact(
            id = 15,
            text = "«Горьковская» — единственная станция с выходом прямо к реке",
            type = MetroFact.FactType.STATION_FACT,
            relatedStationId = 27,
            category = MetroFact.FactCategory.ARCHITECTURE
        ),
        MetroFact(
            id = 16,
            text = "На «Площади Восстания» сохранились уникальные бронзовые бюсты Ленина",
            type = MetroFact.FactType.STATION_FACT,
            relatedStationId = 10,
            category = MetroFact.FactCategory.HISTORICAL
        ),
        MetroFact(
            id = 17,
            text = "«Технологический институт» — первая станция с кросс-платформенной пересадкой",
            type = MetroFact.FactType.STATION_FACT,
            relatedStationId = 13,
            category = MetroFact.FactCategory.TECHNICAL
        ),
        MetroFact(
            id = 18,
            text = "Станция «Пушкинская» украшена скульптурой поэта работы Аникушина",
            type = MetroFact.FactType.STATION_FACT,
            relatedStationId = 12,
            category = MetroFact.FactCategory.ARCHITECTURE
        ),
        MetroFact(
            id = 19,
            text = "«Электросила» получила название от завода-изготовителя электродвигателей",
            type = MetroFact.FactType.STATION_FACT,
            relatedStationId = 33,
            category = MetroFact.FactCategory.HISTORICAL
        ),
        MetroFact(
            id = 20,
            text = "«Приморская» — самая западная станция в центральной части города",
            type = MetroFact.FactType.STATION_FACT,
            relatedStationId = 40,
            category = MetroFact.FactCategory.RECORDS
        ),
        MetroFact(
            id = 21,
            text = "«Владимирская» украшена мозаичными панно на тему защиты Руси",
            type = MetroFact.FactType.STATION_FACT,
            relatedStationId = 11,
            category = MetroFact.FactCategory.ARCHITECTURE
        ),
        MetroFact(
            id = 22,
            text = "«Нарвская» посвящена трудовым подвигам советского народа",
            type = MetroFact.FactType.STATION_FACT,
            relatedStationId = 15,
            category = MetroFact.FactCategory.ARCHITECTURE
        ),
        MetroFact(
            id = 23,
            text = "«Кировский завод» названа в честь расположенного рядом гиганта промышленности",
            type = MetroFact.FactType.STATION_FACT,
            relatedStationId = 16,
            category = MetroFact.FactCategory.HISTORICAL
        ),
        MetroFact(
            id = 24,
            text = "«Политехническая» находится рядом с одним из старейших технических вузов России",
            type = MetroFact.FactType.STATION_FACT,
            relatedStationId = 4,
            category = MetroFact.FactCategory.HISTORICAL
        ),
        MetroFact(
            id = 25,
            text = "«Спортивная» расположена рядом со стадионом «Петровский»",
            type = MetroFact.FactType.STATION_FACT,
            relatedStationId = 64,
            category = MetroFact.FactCategory.GENERAL
        ),
        MetroFact(
            id = 26,
            text = "«Василеостровская» находится на самом большом острове дельты Невы",
            type = MetroFact.FactType.STATION_FACT,
            relatedStationId = 41,
            category = MetroFact.FactCategory.GENERAL
        ),
        MetroFact(
            id = 27,
            text = "«Академическая» расположена в районе, где находятся многие НИИ и вузы",
            type = MetroFact.FactType.STATION_FACT,
            relatedStationId = 3,
            category = MetroFact.FactCategory.GENERAL
        ),
        MetroFact(
            id = 28,
            text = "«Сенная площадь» исторически была местом торговли и народных гуляний",
            type = MetroFact.FactType.STATION_FACT,
            relatedStationId = 29,
            category = MetroFact.FactCategory.HISTORICAL
        ),
        MetroFact(
            id = 29,
            text = "«Ладожская» является пересадочным узлом на Ладожский вокзал",
            type = MetroFact.FactType.STATION_FACT,
            relatedStationId = 57,
            category = MetroFact.FactCategory.TECHNICAL
        ),
        MetroFact(
            id = 30,
            text = "«Новочеркасская» названа по Новочеркасскому проспекту, ведущему к Большеохтинскому мосту",
            type = MetroFact.FactType.STATION_FACT,
            relatedStationId = 56,
            category = MetroFact.FactCategory.HISTORICAL
        ),
        MetroFact(
            id = 31,
            text = "На «Маяковской» установлены уникальные витражи",
            type = MetroFact.FactType.STATION_FACT,
            relatedStationId = 43,
            category = MetroFact.FactCategory.ARCHITECTURE
        ),
        MetroFact(
            id = 32,
            text = "«Гостиный двор» находится под одноимённым старейшим торговым центром России",
            type = MetroFact.FactType.STATION_FACT,
            relatedStationId = 42,
            category = MetroFact.FactCategory.HISTORICAL
        ),
        MetroFact(
            id = 33,
            text = "«Площадь Александра Невского» названа в честь великого полководца",
            type = MetroFact.FactType.STATION_FACT,
            relatedStationId = 44,
            category = MetroFact.FactCategory.HISTORICAL
        ),
        MetroFact(
            id = 34,
            text = "На станции «Чернышевская» в отделке использован розовый мрамор",
            type = MetroFact.FactType.STATION_FACT,
            relatedStationId = 9,
            category = MetroFact.FactCategory.ARCHITECTURE
        ),
        MetroFact(
            id = 35,
            text = "«Обводный канал» — самая молодая станция Фрунзенско-Приморской линии",
            type = MetroFact.FactType.STATION_FACT,
            relatedStationId = 68,
            category = MetroFact.FactCategory.RECORDS
        ),
        MetroFact(
            id = 36,
            text = "Станция «Балтийская» украшена мозаиками на тему морской славы России",
            type = MetroFact.FactType.STATION_FACT,
            relatedStationId = 14,
            category = MetroFact.FactCategory.ARCHITECTURE
        ),
        MetroFact(
            id = 37,
            text = "«Петроградская» расположена рядом с театрами и культурными центрами Петроградской стороны",
            type = MetroFact.FactType.STATION_FACT,
            relatedStationId = 26,
            category = MetroFact.FactCategory.GENERAL
        ),
        MetroFact(
            id = 38,
            text = "«Выборгская» находится в историческом промышленном районе города",
            type = MetroFact.FactType.STATION_FACT,
            relatedStationId = 7,
            category = MetroFact.FactCategory.HISTORICAL
        ),
        MetroFact(
            id = 39,
            text = "«Чернышевская» названа в честь русского писателя и философа Н.Г. Чернышевского",
            type = MetroFact.FactType.STATION_FACT,
            relatedStationId = 9,
            category = MetroFact.FactCategory.HISTORICAL
        ),
        MetroFact(
            id = 40,
            text = "На «Электросиле» установлены уникальные светильники в виде шестеренок",
            type = MetroFact.FactType.STATION_FACT,
            relatedStationId = 33,
            category = MetroFact.FactCategory.ARCHITECTURE
        ),
        MetroFact(
            id = 41,
            text = "«Лесная» получила название от исторического района Лесной, где располагался Лесной институт",
            type = MetroFact.FactType.STATION_FACT,
            relatedStationId = 6,
            category = MetroFact.FactCategory.HISTORICAL
        ),
        MetroFact(
            id = 42,
            text = "«Политехническая» имеет самый длинный эскалаторный наклон среди станций Кировско-Выборгской линии",
            type = MetroFact.FactType.STATION_FACT,
            relatedStationId = 4,
            category = MetroFact.FactCategory.TECHNICAL
        ),
        MetroFact(
            id = 43,
            text = "Во время блокады Ленинграда недостроенные станции метро использовались как бомбоубежища",
            type = MetroFact.FactType.METRO_GENERAL_FACT,
            category = MetroFact.FactCategory.HISTORICAL
        ),
        MetroFact(
            id = 44,
            text = "Первые проекты метро в Петербурге появились ещё в конце XIX века",
            type = MetroFact.FactType.METRO_GENERAL_FACT,
            category = MetroFact.FactCategory.HISTORICAL
        ),
        MetroFact(
            id = 45,
            text = "Интерьеры станций первой очереди создавали лучшие художники и архитекторы СССР",
            type = MetroFact.FactType.METRO_GENERAL_FACT,
            category = MetroFact.FactCategory.ARCHITECTURE
        ),
        MetroFact(
            id = 46,
            text = "В 1990-е годы метро иногда работало до 2 часов ночи в выходные дни",
            type = MetroFact.FactType.METRO_GENERAL_FACT,
            category = MetroFact.FactCategory.HISTORICAL
        ),
        MetroFact(
            id = 47,
            text = "Подвижной состав метро постоянно обновляется - новые поезда типов «НеВа», «Юбилейный»",
            type = MetroFact.FactType.METRO_GENERAL_FACT,
            category = MetroFact.FactCategory.TECHNICAL
        ),
        MetroFact(
            id = 48,
            text = "«Новочеркасская» стала первой станцией, открытой в постсоветский период (1993 год)",
            type = MetroFact.FactType.STATION_FACT,
            relatedStationId = 56,
            category = MetroFact.FactCategory.HISTORICAL
        ),
        MetroFact(
            id = 49,
            text = "Строительство метро в сырых грунтах Петербурга требовало специальных технологий заморозки",
            type = MetroFact.FactType.METRO_GENERAL_FACT,
            category = MetroFact.FactCategory.TECHNICAL
        ),
        MetroFact(
            id = 50,
            text = "«Театральная» украшена скульптурами персонажей русских опер и балетов",
            type = MetroFact.FactType.STATION_FACT,
            relatedStationId = 51,
            category = MetroFact.FactCategory.ARCHITECTURE
        )
    )

    // Кэш для быстрого поиска фактов по станциям
    private val stationFactsCache = mutableMapOf<Int, List<MetroFact>>()

    // Инициализация кэша
    private fun initializeStationCache() {
        if (stationFactsCache.isEmpty()) {
            allFacts
                .filter { it.type == MetroFact.FactType.STATION_FACT && it.relatedStationId != null }
                .groupBy { it.relatedStationId!! }
                .forEach { (stationId, facts) ->
                    stationFactsCache[stationId] = facts
                }
        }
    }

    // Основной метод: получение фактов для конкретной станции
    fun getFactsForStation(stationId: Int): List<MetroFact> {
        initializeStationCache()
        return stationFactsCache[stationId] ?: emptyList()
    }

    // Получить случайный факт о станции
    fun getRandomFactForStation(stationId: Int): MetroFact? {
        val facts = getFactsForStation(stationId)
        return if (facts.isNotEmpty()) facts.random() else null
    }

    // Получить факты по категории для станции
    fun getFactsForStationByCategory(stationId: Int, category: MetroFact.FactCategory): List<MetroFact> {
        return getFactsForStation(stationId).filter { it.category == category }
    }

    // Проверить, есть ли факты о станции
    fun hasFactsForStation(stationId: Int): Boolean {
        return getFactsForStation(stationId).isNotEmpty()
    }

    // Получить количество фактов о станции
    fun getFactsCountForStation(stationId: Int): Int {
        return getFactsForStation(stationId).size
    }

    // Получить все станции, о которых есть факты
    fun getStationsWithFacts(): List<Int> {
        initializeStationCache()
        return stationFactsCache.keys.toList()
    }

    // Получить общие факты (не привязанные к станциям)
    fun getGeneralFacts(): List<MetroFact> {
        return allFacts.filter { it.type == MetroFact.FactType.METRO_GENERAL_FACT }
    }

    // Получить факты по категории
    fun getFactsByCategory(category: MetroFact.FactCategory): List<MetroFact> {
        return allFacts.filter { it.category == category }
    }
}