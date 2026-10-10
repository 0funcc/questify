package com.wrathbyte.questify

import app.cash.sqldelight.db.SqlDriver
import com.wrathbyte.questify.database.QuestifyDatabase
import kotlin.random.Random

class QuestRepository(driver: SqlDriver) {

    private val database = QuestifyDatabase(driver)
    private val queries = database.questTaskQueries

    fun addTask(
        title: String,
        questID: String?,
        description: String?
    ): Task {
        val task = Task(
            id = generateID(),
            questID = questID,
            title = title,
            description = description
        )

        queries.insertTask(
            id = task.id,
            questID = task.questID,
            title = task.title,
            description = task.description,
            isCompleted = task.isCompleted
        )

        return task
    }

    fun getTasks(): List<Task> {
        return queries.selectAllTasks()
            .executeAsList()
            .map { row ->
                Task(
                    id = row.id,
                    questID = row.questID,
                    title = row.title,
                    description = row.description,
                    isCompleted = row.isCompleted
                )
            }
    }

    fun setTaskCompleted(id: String, completed: Boolean) {
        queries.updateTaskCompletion(
            isCompleted = completed,
            id = id
        )
    }

    fun deleteTask(id: String) {
        queries.deleteTask(id)
    }

    private fun generateID(): String {
        return Random.nextLong().toString()
    }
}