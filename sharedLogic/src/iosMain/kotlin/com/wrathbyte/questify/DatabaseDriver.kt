package com.wrathbyte.questify

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.wrathbyte.questify.database.QuestifyDatabase

class DatabaseDriver {
    fun create(): SqlDriver {
        return NativeSqliteDriver(
            schema = QuestifyDatabase.Schema,
            name = "questify.db"
        )
    }
}