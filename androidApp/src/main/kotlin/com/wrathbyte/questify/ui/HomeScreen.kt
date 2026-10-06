package com.wrathbyte.questify.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wrathbyte.questify.Task

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    val demoTasks = listOf(
        Task(
            id = "1",
            title = "Finish CS assignment",
            description = "Complete MST129 questions",
            isCompleted = false
        ),
        Task(
            id = "2",
            title = "Go for a run",
            description = "Run 5 km",
            isCompleted = true
        ),
        Task(
            id = "3",
            title = "Study Swift",
            description = "Learn SwiftUI navigation",
            isCompleted = false
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Active Quests")
                }
            )
        },
        modifier = modifier
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(
                items = demoTasks,
                key = { it.id }
            ) { task ->
                TaskCard(task = task)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
        HomeScreen()
}