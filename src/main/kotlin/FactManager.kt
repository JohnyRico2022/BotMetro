import data.FactsData
import models.MetroFact

object FactManager {

    private val userFactHistory = mutableMapOf<Long, MutableSet<Int>>()

    fun getRandomFactForUser(userId: Long): MetroFact {
        val seenFactIds = userFactHistory.getOrPut(userId) { mutableSetOf() }
        val unseenFacts = FactsData.allFacts.filter { it.id !in seenFactIds }
        val fact = unseenFacts.random()
        seenFactIds.add(fact.id)
        return fact
    }

    // Проверка, что пользователь не прочитал все факты
    fun checkUserProgress(progress: Int): Boolean {
        return (progress == 100)
    }

    fun getProgress(userId: Long): Int {
        val seenCount = userFactHistory[userId]?.size ?: 0
        val totalCount = FactsData.allFacts.size
        return (seenCount.toDouble() / totalCount * 100).toInt()
    }

    fun getUserFactProgress(userId: Long): String {
        val seenCount = userFactHistory[userId]?.size ?: 0
        val totalCount = FactsData.allFacts.size
        val progress = (seenCount.toDouble() / totalCount * 100).toInt()

        return "📊 Изучено фактов: $seenCount/$totalCount ($progress%)"
    }

    fun getProgressBar(progress: Int): String {
        val filled = progress / 10
        return "🚇".repeat(filled) + "⬜".repeat(10 - filled) + " $progress%"
    }

    fun resetProgress(){
        userFactHistory.clear()
    }
}