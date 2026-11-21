package com.wrathbyte.questify

import app.cash.sqldelight.db.SqlDriver
import com.wrathbyte.questify.database.QuestifyDatabase
import kotlin.random.Random

class QuestRepository(driver: SqlDriver) {

    private val database = QuestifyDatabase(driver)
    private val queries = database.questTaskQueries

    fun addQuest(
        title: String,
        questID: String?,
        description: String?
    ): Quest {
        val quest = Quest(
            id = generateID(),
            questID = questID,
            title = title,
            description = description
        )

        queries.insertTask(
            id = quest.id,
            questID = quest.questID,
            title = quest.title,
            description = quest.description,
            isCompleted = quest.isCompleted
        )

        return quest
    }

    fun getQuests(): List<Quest> {
        return queries.selectAllTasks()
            .executeAsList()
            .map { row ->
                Quest(
                    id = row.id,
                    questID = row.questID,
                    title = row.title,
                    description = row.description,
                    isCompleted = row.isCompleted
                )
            }
    }

    fun setQuestCompleted(id: String, completed: Boolean) {
        queries.updateTaskCompletion(
            isCompleted = completed,
            id = id
        )
    }

    fun deleteQuest(id: String) {
        queries.deleteTask(id)
    }

    @Deprecated("Use addQuest instead", ReplaceWith("addQuest(title, questID, description)"))
    fun addTask(title: String, questID: String?, description: String?): Quest =
        addQuest(title, questID, description)

    @Deprecated("Use getQuests instead", ReplaceWith("getQuests()"))
    fun getTasks(): List<Quest> = getQuests()

    @Deprecated("Use setQuestCompleted instead", ReplaceWith("setQuestCompleted(id, completed)"))
    fun setTaskCompleted(id: String, completed: Boolean) = setQuestCompleted(id, completed)

    @Deprecated("Use deleteQuest instead", ReplaceWith("deleteQuest(id)"))
    fun deleteTask(id: String) = deleteQuest(id)

    private fun generateID(): String {
        return Random.nextLong().toString()
    }
}
