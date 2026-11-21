package com.wrathbyte.questify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.wrathbyte.questify.database.QuestifyDatabase
import com.wrathbyte.questify.ui.AppNavigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        val driver = AndroidSqliteDriver(
            schema = QuestifyDatabase.Schema,
            context = applicationContext,
            name = "questify.db",
        )
        val repository = QuestRepository(driver)

        setContent {
            AppNavigation(repository = repository)
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}