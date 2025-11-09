import com.pengrad.telegrambot.TelegramBot
import com.pengrad.telegrambot.UpdatesListener
import com.pengrad.telegrambot.model.Update
import com.pengrad.telegrambot.model.request.KeyboardButton
import com.pengrad.telegrambot.model.request.ParseMode
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup
import com.pengrad.telegrambot.request.SendMessage
import com.pengrad.telegrambot.request.SendPhoto
import data.FactsData
import data.LinesData
import data.StationData
import models.MetroFact
import models.MetroStation
import models.Route
import java.io.File

class MetroBot(private val bot: TelegramBot) {

    // Храним состояние поиска для каждого пользователя
    private val usersSearchingStations = mutableSetOf<Long>()
    private var usersStartSearchRoute = mutableMapOf<Long, Boolean>()

    fun start() {
        bot.setUpdatesListener { updates ->
            updates.forEach { update ->
                handleUpdate(update)
            }
            UpdatesListener.CONFIRMED_UPDATES_ALL
        }
    }

    fun handleUpdate(update: Update) {
        // Проверяем, есть ли сообщение и текст
        if (update.message() != null && update.message().text() != null) {
            val messageText = update.message().text()
            val chatId = update.message().chat().id()

            when (messageText) {
                "/start" -> hello(chatId)
                "/help" -> sendHelp(chatId)
                "/reset" -> resetProgress(chatId)
                "\uD83D\uDCCD Построить маршрут" -> startRouteBuilding(chatId)
                "🚉 Информация о станции" -> askForStationName(chatId)
                "💡 Случайный факт" -> randomFact(chatId)
                "🗺️ Схема метрополитена" -> showMap(chatId)
                "ℹ️ Помощь" -> sendInfo(chatId)
                "❌ Отменить поиск" -> cancelSearch(chatId)
                "❌ Отменить построение маршрута" -> cancelRoute(chatId)
                else -> {
                    // Проверяем, не ищет ли пользователь станцию
                    if (isLookingForStation(chatId))
                        handleStationSearch(chatId, messageText)
                    // Проверяем, не ищет ли пользователь маршрут
                    else if (usersStartSearchRoute.containsKey(chatId) && messageText.contains("."))
                        handleRouteInput(chatId, messageText)
                    else
                        sendUnknownCommand(chatId)
                }
            }
        }
    }

    private fun showMap(chatId: Long) {
        try {
            val mapPath = "src/main/resources/map/metromap.jpeg"
            val mapFile = File(mapPath)

            if (mapFile.exists()) {
                val mapMessage = SendPhoto(chatId, mapFile)
                    .caption(
                        """
                    *🗺️ Схема Петербургского метрополитена*
                
                    🎯 *Чтобы построить маршрут -* нажмите соответствующую кнопку ниже ⬇️
                """.trimIndent()
                    )
                    .parseMode(ParseMode.Markdown)

                bot.execute(mapMessage)

            } else {
                showMainMenu(chatId)
            }

        } catch (e: Exception) {
            println("Ошибка отправки схемы метро: ${e.message}")
            e.printStackTrace()
        }
    }


    private fun hello(chatId: Long) {
        //создаем 2 сообщения: Приветствие и "выбирите действие" с кнопками
        sendHelloMessage(chatId)
        showMainMenu(chatId)
    }

    private fun sendHelloMessage(chatId: Long) {

        val welcomeMessage = SendMessage(
            chatId,
            """
                🚇 Привет! Я бот-помощник по метрополитену Санкт-Петербурга!
                
                Я помогу вам:
                • 🗺️ Построить оптимальный маршрут между станциями
                • 🚉 Узнать информацию о любой станции метро
                • 💡 Открыть интересные факты о питерском метро
                • 📊 Узнать справочную информацию.
                
            """.trimIndent()
        )
            .parseMode(ParseMode.Markdown)

        bot.execute(welcomeMessage)
    }

    private fun showMainMenu(chatId: Long) {
        usersSearchingStations.remove(chatId) // Выходим из режима поиска

        val keyboard = ReplyKeyboardMarkup(
            arrayOf(KeyboardButton("\uD83D\uDCCD Построить маршрут")),
            arrayOf(KeyboardButton("🚉 Информация о станции")),
            arrayOf(KeyboardButton("🗺️ Схема метрополитена")),
            arrayOf(
                KeyboardButton("💡 Случайный факт"),
                KeyboardButton("ℹ️ Помощь")
            )
        )
            .resizeKeyboard(true)
            .oneTimeKeyboard(false)

        val message = SendMessage(chatId, " *Выберите действие ниже* ⬇\uFE0F ")
            .parseMode(ParseMode.Markdown)
            .replyMarkup(keyboard)

        bot.execute(message)
    }

    private fun sendHelp(chatId: Long) {
        val helpMessage = SendMessage(
            chatId,
            """
        ℹ️ *Помощь по командам*

        *Основные команды:*
        /start - Начать работу с ботом
        /help - Показать эту справку
        /reset - Сбросить прогресс по фактам 

        *Действия через кнопки меню ниже* ⬇️
        
         📍 *Построить маршрут* - оптимальный маршрут между выбранными станциями
         🚉 *Информация о станции* - подробная информация о выбранной станции
         🗺️ *Схема метрополитена* - получить схему метрополитена
         💡 *Случайный факт* - узнать много интересного о метрополитене
         ℹ️ *Помощь* - полезная информация

        """.trimIndent()
        )
            .parseMode(ParseMode.Markdown)

        bot.execute(helpMessage)
    }

    private fun resetProgress(chatId: Long) {
        val resetMessage = SendMessage(
            chatId,
            """
        ❗ Вы сбросили свой прогресс по фактам!
                """
        )
            .parseMode(ParseMode.Markdown)

        bot.execute(resetMessage)
        FactManager.resetProgress()
    }

    private fun startRouteBuilding(chatId: Long) {
        usersStartSearchRoute[chatId] = true

        val message = SendMessage(
            chatId,
            """
        🗺️ *Построение маршрута*

        💡 Для поиска оптимального маршрута *введите две станции через точку*

        🎯 *Примеры:*
        `адмиралтейская.московская`
        `невский проспект.площадь восстания`  
        `автово.рыбацкое`
        `кировский завод.чернышевская`

        Я помогу найти оптимальный путь! 🚇
        """.trimIndent()
        )
            .parseMode(ParseMode.Markdown)
            .replyMarkup(keyCancel())

        bot.execute(message)
    }

    private fun askForStationName(chatId: Long) {

        usersSearchingStations.add(chatId) // Добавляем пользователя в режим поиска станции

        val keyboard = ReplyKeyboardMarkup(KeyboardButton("↩️ Назад в меню"))
            .resizeKeyboard(true)
            .oneTimeKeyboard(true)

        val stationMessage = SendMessage(
            chatId,
            """
        🚉 *Поиск информации о станции*

        Напишите название станции, и я покажу:
        • 📍   На какой линии находится
        • 🔢  Номер линии
        • 🎨  Цвет линии
        • 📅  Год открытия
        • 📏  Глубину залегания
        • 🔄  Доступные пересадки
        • 💡   Интересные факты

        Например: _Автово_ или _Невский проспект_
        """.trimIndent()
        )
            .parseMode(ParseMode.Markdown)
            .replyMarkup(keyboard)

        bot.execute(stationMessage)
    }

    private fun handleStationSearch(chatId: Long, stationName: String) {
        // Проверяем, не хочет ли пользователь вернуться в меню
        if (stationName == "↩️ Назад в меню") {
            usersSearchingStations.remove(chatId)
            showMainMenu(chatId)
            return
        }

        val foundStations = StationData.findStationsByName(stationName)  // Ищем станции по названию
        when {
            foundStations.isEmpty() -> {
                val stName: String = stationName

                val message = SendMessage(
                    chatId,
                    """
                ❌ *Станция не найдена*
                
                Станция "_'$stName'_" не найдена в базе данных.
                
                🔍 Попробуйте:
                • Проверить написание 
                • Использовать часть названия
                """.trimIndent()
                ).parseMode(ParseMode.Markdown)
                bot.execute(message)
            }

            foundStations.size == 1 -> {
                // Нашли одну станцию - показываем полную информацию
                showStationInfo(chatId, foundStations[0])
                usersSearchingStations.remove(chatId) // Выходим из режима поиска
            }

            else -> showStationsList(chatId, foundStations) // Нашли несколько станций - показываем список для выбора
        }
    }

    private fun showStationInfo(chatId: Long, station: MetroStation) {
        val line = LinesData.findLineById(station.lineId)
        val stationFacts = FactsData.getFactsForStation(station.id)
        val numberLine: String = LinesData.getEmojiNumber(line?.id ?: 9)


        // todo переделать формат
        val messageText = buildString {
            append("🚇 *${station.name}*\n\n")

            append("• *Линия:* ${line?.name ?: "Неизвестно"}\n")
            append("• *Номер линии:*  $numberLine  \n")  // показ через эмоджи
            append("• *Цвет линии:* ${line?.color} \n")
            append("• *Год открытия:* ${station.openingYear} г.  \n")
            append("• *Глубина залегания:* ${station.depth} м \n\n")

            if (!station.transferTo.isNullOrEmpty()) {
                //todo добавить в конце слово линия
                append("🔄 *Пересадки на:*\n")
                station.transferTo!!.forEach { transferId ->
                    val transferStation = StationData.findStationById(transferId)
                    val transferLine = transferStation?.let { LinesData.findLineById(it.lineId) }
                    append("• ${transferStation?.name} (${transferLine?.name})\n")
                }
                append("\n")
            } else {
                append("🔄 *Пересадки:* нет\n\n")
            }

            if (stationFacts.isNotEmpty()) {
                append("💡 *Интересный факт:*\n")
                append("${stationFacts.first().text}\n\n")
            }
        }

        val message = SendMessage(chatId, messageText)
            .parseMode(ParseMode.Markdown)

        bot.execute(message)
        showMainMenu(chatId) // Показываем меню с кнопками
    }

    private fun showStationsList(chatId: Long, stations: List<MetroStation>) {

        println("${stations.size}")

        if (stations.size in 2..4) {
            println("У нас ${stations.size} станций")
            showStationsWithButtons(chatId, stations)
        } else {
            sendTooManySearchResults(chatId, stations.size)
            usersSearchingStations.remove(chatId) // Выходим из режима поиска
            askForStationName(chatId)
        }
    }

    private fun showStationsWithButtons(chatId: Long, stations: List<MetroStation>) {

        // Создаем кнопки для станций
        val stationsButtons = stations.map { station ->
            arrayOf(KeyboardButton(station.name))
        }.toTypedArray()

        // Добавляем кнопку "Отменить"
        val cancelButton = arrayOf(KeyboardButton("❌ Отменить поиск"))

        val allButtons = stationsButtons + cancelButton

        val keyboard = ReplyKeyboardMarkup(
            *allButtons,
        ).resizeKeyboard(true)
            .oneTimeKeyboard(true)

        val message = SendMessage(
            chatId,
            """
        🔍 *Найдено ${stations.size} станции:*
        
        Выберите нужную станцию из списка ниже: ⬇️
        """.trimIndent()
        ).parseMode(ParseMode.Markdown)
            .replyMarkup(keyboard)

        bot.execute(message)
    }

    private fun cancelSearch(chatId: Long) {
        usersSearchingStations.remove(chatId)
        showMainMenu(chatId)
    }

    private fun sendTooManySearchResults(chatId: Long, stationSize: Int) {
        val message = SendMessage(
            chatId,
            """
             🔍 Мы нашли для вас * $stationSize станций! *
             
             Это очень много и мы не можем их все обработать! 😞
             Пожалуйста, уточните свой запрос в названии станции, сделав его более конкретным! 🎯
            """.trimIndent()
        )
            .parseMode(ParseMode.Markdown)

        bot.execute(message)
    }

    private fun sendInfo(chatId: Long) {
        val helpMessage = SendMessage(
            chatId,
            """
        *📞 Контактная информация метрополитена:*

        • 📞 Единый call-центр: +7 (812) 301-97-00
        • 🌐 Официальный сайт: metro.spb.ru
        • 📧 Эл. почта: info@metro.spb.ru

        *🕒 Режим работы метро:*

        • 🟢 Открытие: 5:30 утра
        • 🔴 Закрытие: 0:30 ночи
        • 📅 Работает: ежедневно

        *💡 Полезные советы:*

        • 🎫 Проездные билеты можно купить в кассах и терминалах
        • ♿ Все станции оборудованы для маломобильных граждан
        • 📱 Бесплатный Wi-Fi на всех станциях
        • 🔄 Время пересадки между линиями ~3-5 минут

        *🚨 Экстренные службы:*

        • 🚓 Полиция: 102 (с мобильного), 02 (с городского)
        • 🚑 Скорая помощь: 103 (с мобильного), 03 (с городского)
        • 🚒 Пожарная служба: 101 (с мобильного), 01 (с городского)
        """.trimIndent()
        )
            .parseMode(ParseMode.Markdown)

        val safetyMessage = SendMessage(
            chatId,
            """
        *🛡️ Правила безопасности в метро:*

        • ⚠️ Не подходите к краю платформы
        • 🚫 Не заходите за ограничительную линию
        • 👶 Держите детей за руку
        • 🎧 Снимайте наушники при подходе к поезду
        • 🛄 Не оставляйте вещи без присмотра

        *🚇 В вагоне поезда:*

        • 🪑 Уступайте места пожилым и беременным
        • 📱 Используйте наушники при прослушивании
        • 🚭 Курение запрещено
        • 🍕 Приём пищи не рекомендуется
        """.trimIndent()
        )
            .parseMode(ParseMode.Markdown)

        bot.execute(helpMessage)
        bot.execute(safetyMessage)
    }

    private fun randomFact(chatId: Long) {
        val progress = FactManager.getProgress(chatId)
        val checkProgress: Boolean = FactManager.checkUserProgress(progress)

        if (!checkProgress)
            sendFact(chatId)
        else
            sendCongratulations(chatId)
    }

    private fun sendFact(chatId: Long) {
        val fact = FactManager.getRandomFactForUser(chatId)
        val progressString = FactManager.getUserFactProgress(chatId)
        val progressNow = FactManager.getProgress(chatId)
        val progressBar = FactManager.getProgressBar(progressNow)
        //todo переделать формат
        val messageText = buildString {
            append("💡 ")
            if (fact.type == MetroFact.FactType.STATION_FACT)
                append("*Факт о станции*")
            else
                append("*Факт о метро*")

            append("\n\n")
            append(fact.text)
            append("\n\n")
            append(progressString)
            append("\n\n")
            append(progressBar)
            append("\n\n")
            append("*Хотите ещё факт?* Нажмите кнопку снова! 🔄 ")
        }

        val factMessage = SendMessage(chatId, messageText)
            .parseMode(ParseMode.Markdown)

        bot.execute(factMessage)
    }

    private fun sendCongratulations(chatId: Long) {
        val message = SendMessage(
            chatId,
            """
        💡 *Поздравляем!* 🎉

        Вы изучили все 50 фактов о питерском метро! 
        Вы настоящий эксперт метрополитена! 🚇✨

        Очень скоро мы добавим новые интересные факты!

        🔄 Хотите начать заново?
        Воспользуйтесь командой:  /reset
        
        """.trimIndent()
        )
            .parseMode(ParseMode.Markdown)

        bot.execute(message)
    }

    private fun sendUnknownCommand(chatId: Long) {
        val unknowMessage = SendMessage(
            chatId,
            """
                🤔 Не могу распознать ваш запрос!

                Для удобства воспользуйтесь кнопками меню ниже ⬇️  

                Чтобы начать общение заново, перезапустите бота - /start 
                """.trimIndent()
        )
            .parseMode(ParseMode.Markdown)

        bot.execute(unknowMessage)

        showMainMenu(chatId)
    }

    private fun isLookingForStation(chatId: Long): Boolean {
        return usersSearchingStations.contains(chatId)
    }

    private fun handleRouteInput(chatId: Long, messageText: String) {
        try {
            // Разделяем по точке
            val parts: List<String> = messageText.split(".").map { it.trim() }

            if (parts.size != 2) {
                showRouteFormatError(chatId)   //todo доделать
                return
            }

            val fromInput: String = parts[0]
            val toInput: String = parts[1]

            val fromStations = StationData.findStationsByName(fromInput)
            val toStations = StationData.findStationsByName(toInput)

            when {
                fromStations.isEmpty() -> showStationNotFound(chatId, fromInput, true)
                toStations.isEmpty() -> showStationNotFound(chatId, toInput, false)
                fromStations.size > 1 -> showMultipleStationsFound(chatId, fromStations, true, fromInput)
                toStations.size > 1 -> showMultipleStationsFound(chatId, toStations, false, toInput)
                else -> {
                    //Защита от ввода одинаковых станций
                    if (fromStations[0].id == toStations[0].id) {
                        sendSameStations(chatId, fromStations[0].name)
                    } else {
                        // Все ок - строим маршрут!
                        buildAndShowRoute(chatId, fromStations[0], toStations[0])
                        usersStartSearchRoute.remove(chatId)
                    }
                }
            }

        } catch (e: Exception) {
            showRouteFormatError(chatId)
        }
    }

    private fun sendSameStations(chatId: Long, stationName: String) {
        val message = SendMessage(
            chatId,
            """
        ❗ *Одинаковые станции!*
        
        Вы указали станцию *$stationName* и для отправления, и для назначения.
        
        💡 *Попробуйте снова!*
        """.trimIndent()
        ).parseMode(ParseMode.Markdown)
            .replyMarkup(keyCancel())

        bot.execute(message)
    }

    private fun buildAndShowRoute(chatId: Long, stationFrom: MetroStation, stationTo: MetroStation) {

        if (stationFrom.lineId == stationTo.lineId)  //станции на одной линии
            findRouteOnSameLine(chatId, stationFrom, stationTo)
        else
            findRouteOnDifferentLine(chatId, stationFrom, stationTo)
    }

    private fun findRouteOnSameLine(chatId: Long, stationFrom: MetroStation, stationTo: MetroStation) {

        val line = LinesData.findLineById(stationFrom.lineId)!!
        val stationIds = line.stationIds // id всех станций на линии

        // Находим ИНДЕКСЫ станций в списке линии!
        val fromIndex = stationIds.indexOf(stationFrom.id)
        val toIndex = stationIds.indexOf(stationTo.id)

        val route: Route
        val routePath = mutableListOf<MetroStation>()
        var totalTime = 0

        if (fromIndex < toIndex) { //прямой поиск
            //собираем станции по порядку
            for (i in fromIndex..toIndex) {
                val stationId = stationIds[i]
                val station = StationData.findStationById(stationId)!!
                routePath.add(station)

                if (i < toIndex) {
                    val nextStationId = stationIds[i + 1]
                    val travelTime = station.neighbors[nextStationId] ?: 15
                    totalTime += travelTime
                }
            }
            route = Route(routePath, totalTime, 0)
        } else {//обратный поиск
            //собираем станции в обратном порядке
            for (i in fromIndex downTo toIndex) {
                val stationId = stationIds[i]

                val station = StationData.findStationById(stationId)!!
                routePath.add(station)

                if (i > toIndex) {
                    val prevStationId = stationIds[i - 1]
                    val travelTime = station.neighbors[prevStationId] ?: 9
                    totalTime += travelTime
                }
            }
            route = Route(routePath, totalTime, 0)
        }
        sendResultRouteOnSameLine(chatId, route)
    }

    private fun sendResultRouteOnSameLine(chatId: Long, route: Route) {

        val line = LinesData.findLineById(route.path[0].lineId)!!
        val toStation = route.path[route.path.size - 1]
        val routePath = route.path.joinToString(" -> ") { station ->
            if (station.id == toStation.id) "*${station.name}*" else station.name
        }

        val routeMessage = SendMessage(
            chatId,
            """
            *🎯 Ваш маршрут построен!*

            • От: ${route.path[0].name}
            • До: ${route.path[route.path.size - 1].name}

            *📊 Информация о маршруте:*
            
            • Проехать станций: ${route.path.size - 2}, ваша ${route.path.size - 1}-я
            • Время в пути: ${route.totalTime} мин
            • Пересадок: нет

            *🗺️ Ваш маршрут:*
            
            • Линия ${line.id} (${line.name})
            • $routePath
            
            🚇 Счастливого пути!
            """.trimIndent()
        )
            .parseMode(ParseMode.Markdown)

        bot.execute(routeMessage)
        showMainMenu(chatId)
    }

    private fun findRouteOnDifferentLine(chatId: Long, stationFrom: MetroStation, stationTo: MetroStation) {

        //todo делаем поиск маршрута


        sendResultRouteOnDifferentLine(chatId, stationFrom, stationTo)
    }

    private fun sendResultRouteOnDifferentLine(chatId: Long, stationFrom: MetroStation, stationTo: MetroStation) {
        val routeMessage = SendMessage(
            chatId,
            """
                Вы хотите построит маршрут от станции * ${stationFrom.name} * до станции * ${stationTo.name} *
                
                😔 Данная функция находится в разработке!
                
                Скоро это можно будет сделать!!!!!
            """.trimIndent()
        )
            .parseMode(ParseMode.Markdown)

        bot.execute(routeMessage)
    }

    private fun showStationNotFound(chatId: Long, station: String, status: Boolean) {
        val stationStatus = if (status) "отправления" else "назначения"

        val message = SendMessage(
            chatId,
            """
             💡 Станция $stationStatus * $station * не найдена!
             
             Пожалуйста, задайте свой запрос точнее!
            """.trimIndent()
        )
            .parseMode(ParseMode.Markdown)
            .replyMarkup(keyCancel())

        bot.execute(message)
    }

    private fun showMultipleStationsFound(
        chatId: Long,
        stations: List<MetroStation>,
        status: Boolean,
        enteredName: String
    ) {
        val stationStatus = if (status) "отправления" else "назначения"

        val message = SendMessage(
            chatId,
            """
             Найдено: ${stations.size} станций $stationStatus!
             
             Вы ввели: _ $enteredName _
             
             💡 Пожалуйста, уточните название станции * $stationStatus * 
              
            """.trimIndent()
        )
            .parseMode(ParseMode.Markdown)
            .replyMarkup(keyCancel())

        bot.execute(message)
    }

    private fun showRouteFormatError(chatId: Long) {
        val message = SendMessage(
            chatId,
            """
            ❗ Не удалось распознать запрос!
             
            💡 Для поиска оптимального маршрута *введите две станции через точку*

            🎯 *Примеры:*
            `адмиралтейская.московская`
            `невский проспект.площадь восстания`  
            `автово.рыбацкое`
            `кировский завод.чернышевская`
            """.trimIndent()
        )
            .parseMode(ParseMode.Markdown)
            .replyMarkup(keyCancel())

        bot.execute(message)
    }

    private fun keyCancel(): ReplyKeyboardMarkup {
        val keyboard = ReplyKeyboardMarkup(KeyboardButton("❌ Отменить построение маршрута"))
            .resizeKeyboard(true)
            .oneTimeKeyboard(true)
        return keyboard
    }

    private fun cancelRoute(chatId: Long) {
        usersStartSearchRoute.remove(chatId)

        val message = SendMessage(
            chatId,
            """
             ❗ Вы отменили поиск маршрута!
            """.trimIndent()
        )
            .parseMode(ParseMode.Markdown)

        bot.execute(message)
        showMainMenu(chatId)
    }
}