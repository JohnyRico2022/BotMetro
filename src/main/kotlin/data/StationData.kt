package data

import models.MetroStation

object StationData {

    val stations = listOf(
        // Линия 1 - Кировско-Выборгская
        MetroStation(1, "Девяткино", 1, neighbors = mapOf(2 to 2), null, 1978, 0),
        MetroStation(2, "Гражданский проспект", 1, neighbors = mapOf(1 to 2, 3 to 2), null, 1978, 64),
        MetroStation(3, "Академическая", 1, neighbors = mapOf(2 to 2, 4 to 2), null, 1975, 64),
        MetroStation(4, "Политехническая", 1, neighbors = mapOf(3 to 2, 5 to 2), null, 1975, 65),
        MetroStation(5, "Площадь Мужества", 1, neighbors = mapOf(4 to 2, 6 to 2), null, 1975, 67),
        MetroStation(6, "Лесная", 1, neighbors = mapOf(5 to 2, 7 to 2), null, 1975, 64),
        MetroStation(7, "Выборгская", 1, neighbors = mapOf(6 to 2, 8 to 2), null, 1975, 67),
        MetroStation(8, "Площадь Ленина", 1, neighbors = mapOf(7 to 2, 9 to 2), null, 1958, 71),
        MetroStation(9, "Чернышевская", 1, neighbors = mapOf(8 to 2, 10 to 2), null, 1958, 70),
        MetroStation(
            10, "Площадь Восстания", 1, neighbors = mapOf(9 to 2, 11 to 2),
            transferTo = listOf(43), 1955, 58
        ),
        MetroStation(
            11, "Владимирская", 1, neighbors = mapOf(10 to 2, 12 to 2),
            transferTo = listOf(52), 1955, 55
        ),
        MetroStation(
            12, "Пушкинская", 1, neighbors = mapOf(11 to 2, 13 to 2),
            transferTo = listOf(66), 1956, 60
        ),
        MetroStation(
            13, "Технологический институт 1", 1, neighbors = mapOf(12 to 2, 14 to 2),
            transferTo = listOf(30), 1955, 60
        ),
        MetroStation(14, "Балтийская", 1, neighbors = mapOf(13 to 2, 15 to 2), null, 1955, 44),
        MetroStation(15, "Нарвская", 1, neighbors = mapOf(14 to 2, 16 to 2), null, 1955, 52),
        MetroStation(16, "Кировский завод", 1, neighbors = mapOf(15 to 2, 17 to 2), null, 1955, 52),
        MetroStation(17, "Автово", 1, neighbors = mapOf(16 to 2, 18 to 2), null, 1955, 12),
        MetroStation(18, "Ленинский проспект", 1, neighbors = mapOf(17 to 2, 19 to 2), null, 1977, 8),
        MetroStation(19, "Проспект Ветеранов", 1, neighbors = mapOf(18 to 2), null, 1977, 8),

        // Линия 2 - Московско-Петроградская
        MetroStation(20, "Парнас", 2, neighbors = mapOf(21 to 2), null, 2006, 0),
        MetroStation(21, "Проспект Просвещения", 2, neighbors = mapOf(20 to 2, 22 to 2), null, 1988, 65),
        MetroStation(22, "Озерки", 2, neighbors = mapOf(21 to 2, 23 to 2), null, 1988, 59),
        MetroStation(23, "Удельная", 2, neighbors = mapOf(22 to 2, 24 to 2), null, 1982, 64),
        MetroStation(24, "Пионерская", 2, neighbors = mapOf(23 to 2, 25 to 2), null, 1982, 67),
        MetroStation(25, "Чёрная речка", 2, neighbors = mapOf(24 to 2, 26 to 2), null, 1982, 67),
        MetroStation(26, "Петроградская", 2, neighbors = mapOf(25 to 2, 27 to 2), null, 1963, 53),
        MetroStation(27, "Горьковская", 2, neighbors = mapOf(26 to 2, 28 to 2), null, 1963, 53),
        MetroStation(
            28, "Невский проспект", 2, neighbors = mapOf(27 to 2, 29 to 2),
            transferTo = listOf(42), 1963, 63
        ),
        MetroStation(
            29, "Сенная площадь", 2, neighbors = mapOf(28 to 2, 30 to 2),
            transferTo = listOf(51, 65), 1963, 55
        ),
        MetroStation(
            30, "Технологический институт 2", 2, neighbors = mapOf(29 to 2, 31 to 2),
            transferTo = listOf(13), 1961, 60
        ),
        MetroStation(31, "Фрунзенская", 2, neighbors = mapOf(30 to 2, 32 to 2), null, 1961, 39),
        MetroStation(32, "Московские ворота", 2, neighbors = mapOf(31 to 2, 33 to 2), null, 1961, 35),
        MetroStation(33, "Электросила", 2, neighbors = mapOf(32 to 2, 34 to 2), null, 1961, 35),
        MetroStation(34, "Парк Победы", 2, neighbors = mapOf(33 to 2, 35 to 2), null, 1961, 35),
        MetroStation(35, "Московская", 2, neighbors = mapOf(34 to 2, 36 to 2), null, 1969, 29),
        MetroStation(36, "Звёздная", 2, neighbors = mapOf(35 to 2, 37 to 2), null, 1972, 22),
        MetroStation(37, "Купчино", 2, neighbors = mapOf(36 to 2), null, 1972, 0),

        // Линия 3 - Невско-Василеостровская
        MetroStation(38, "Беговая", 3, neighbors = mapOf(39 to 2), null, 2018, 17),
        MetroStation(39, "Зенит", 3, neighbors = mapOf(38 to 2, 40 to 2), null, 2018, 17),
        MetroStation(40, "Приморская", 3, neighbors = mapOf(39 to 2, 41 to 2), null, 1979, 68),
        MetroStation(41, "Василеостровская", 3, neighbors = mapOf(40 to 2, 42 to 2), null, 1967, 64),
        MetroStation(
            42, "Гостиный двор", 3, neighbors = mapOf(41 to 2, 43 to 2),
            transferTo = listOf(28), 1967, 56
        ),
        MetroStation(
            43, "Маяковская", 3, neighbors = mapOf(42 to 2, 44 to 2),
            transferTo = listOf(10), 1967, 56
        ),
        MetroStation(
            44, "Площадь Александра Невского 1", 3, neighbors = mapOf(43 to 2, 45 to 2),
            transferTo = listOf(54), 1967, 54
        ),
        MetroStation(45, "Елизаровская", 3, neighbors = mapOf(44 to 2, 46 to 2), null, 1970, 62),
        MetroStation(46, "Ломоносовская", 3, neighbors = mapOf(45 to 2, 47 to 2), null, 1970, 65),
        MetroStation(47, "Пролетарская", 3, neighbors = mapOf(46 to 2, 48 to 2), null, 1981, 72),
        MetroStation(48, "Обухово", 3, neighbors = mapOf(47 to 2, 49 to 2), null, 1981, 62),
        MetroStation(49, "Рыбацкое", 3, neighbors = mapOf(48 to 2), null, 1984, 0),

        // Линия 4 - Лахтинско-Правобережная
        MetroStation(50, "Горный институт", 4, neighbors = mapOf(51 to 2), null, 2024, 71),
        MetroStation(
            51, "Спасская", 4, neighbors = mapOf(50 to 2, 52 to 2),
            transferTo = listOf(29, 65), 2009, 60
        ),
        MetroStation(
            52, "Достоевская", 4, neighbors = mapOf(51 to 2, 53 to 2),
            transferTo = listOf(11), 1991, 62
        ),
        MetroStation(53, "Лиговский проспект", 4, neighbors = mapOf(52 to 2, 54 to 2), null, 1991, 66),
        MetroStation(
            54, "Площадь Александра Невского 2", 4, neighbors = mapOf(53 to 2, 55 to 2),
            transferTo = listOf(44), 85, 60
        ),
        MetroStation(55, "Новочеркасская", 4, neighbors = mapOf(54 to 2, 56 to 2), null, 1985, 61),
        MetroStation(56, "Ладожская", 4, neighbors = mapOf(55 to 2, 57 to 2), null, 1985, 61),
        MetroStation(57, "Проспект Большевиков", 4, neighbors = mapOf(56 to 2, 58 to 2), null, 1985, 68),
        MetroStation(58, "Улица Дыбенко", 4, neighbors = mapOf(57 to 2), null, 1987, 61),

        // Линия 5 - Фрунзенско-Приморская
        MetroStation(59, "Комендантский проспект", 5, neighbors = mapOf(60 to 2), null, 2005, 75),
        MetroStation(60, "Старая Деревня", 5, neighbors = mapOf(59 to 2, 61 to 2), null, 1999, 61),
        MetroStation(61, "Крестовский остров", 5, neighbors = mapOf(60 to 2, 62 to 2), null, 1999, 49),
        MetroStation(62, "Чкаловская", 5, neighbors = mapOf(61 to 2, 63 to 2), null, 1997, 60),
        MetroStation(63, "Спортивная", 5, neighbors = mapOf(62 to 2, 64 to 2), null, 1997, 64),
        MetroStation(64, "Адмиралтейская", 5, neighbors = mapOf(63 to 2, 65 to 2), null, 2011, 86),
        MetroStation(
            65, "Садовая", 5, neighbors = mapOf(64 to 2, 66 to 2),
            transferTo = listOf(29, 51), 1991, 71
        ),
        MetroStation(
            66, "Звенигородская", 5, neighbors = mapOf(65 to 2, 67 to 2),
            transferTo = listOf(12), 2008, 57
        ),
        MetroStation(67, "Обводный канал", 5, neighbors = mapOf(66 to 2, 68 to 2), null, 2010, 61),
        MetroStation(68, "Волковская", 5, neighbors = mapOf(67 to 2, 69 to 2), null, 2008, 61),
        MetroStation(69, "Бухарестская", 5, neighbors = mapOf(68 to 2, 70 to 2), null, 2012,65),
        MetroStation(70, "Международная", 5, neighbors = mapOf(69 to 2, 71 to 2), null,2012,65),
        MetroStation(71, "Проспект Славы", 5, neighbors = mapOf(70 to 2, 72 to 2), null, 2019, 59),
        MetroStation(72, "Дунайская", 5, neighbors = mapOf(71 to 2, 73 to 2), null, 2019, 17),
        MetroStation(73, "Шушары", 5, neighbors = mapOf(72 to 2), null, 2019, 0),
    )
        // Линия 6 - Красносельско-Калининская
//        MetroStation(74, "Путиловская", 6, neighbors = mapOf(76 to 2)),
//        MetroStation(76, "Юго-Западная", 6, neighbors = mapOf(75 to 2))


    // Кэш для быстрого поиска
    private val stationCache = mutableMapOf<Int, MetroStation>()

    // Инициализация кэша
    private fun initializeCache() {
        if (stationCache.isEmpty()) {
            stations.forEach { station ->
                stationCache[station.id] = station
            }
        }
    }

    // Поиск станции по ID
    fun findStationById(id: Int): MetroStation? {
        initializeCache()
        return stationCache[id]
    }

    // Поиск станций по названию (частичное совпадение)
    fun findStationsByName(name: String): List<MetroStation> {
        return stations.filter {
            it.name.contains(name, ignoreCase = true)
        }
    }
}