package com.wrathbyte.questify.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AddQuestCard(
    onDone: (name: String, description: String) -> Unit = { _, _ -> },
    modifier: Modifier = Modifier
) {
    // 1. Declare state for inputs
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    val focusRequester = remember { FocusRequester() }
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 2. Centered Heading
        Text(
            text = "Add Quest",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        // 3. Name Input Field
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Quest Name") },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 4. Optional Description Input Field
        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description (optional)") },
            minLines = 3,
            modifier = Modifier.fillMaxWidth()
        )

        // 5. Flexible Spacer to push the Done button to the bottom
        Spacer(modifier = Modifier.weight(1f))

        // 6. Done Button
        Button(
            onClick = { onDone(name, description) },
            enabled = name.isNotBlank(), // Optional: only enable if name is entered
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Done")
        }
    }
}
@Preview
@Composable
fun AddQuestCardPreview() {
    AddQuestCard()
}

