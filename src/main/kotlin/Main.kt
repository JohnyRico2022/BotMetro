import com.pengrad.telegrambot.TelegramBot

fun main(args: Array<String>) {
    println("🚀 Запуск бота...")

    val botToken = "MY_TOKEN"
    val telegramBot = TelegramBot(botToken)
    val metroBot = MetroBot(telegramBot)

    try {
        metroBot.start()
        println("✅ Бот успешно запущен!")
    } catch (e: Exception) {
        println("❌ Ошибка при запуске бота: ${e.message}")
        e.printStackTrace()
    }
}