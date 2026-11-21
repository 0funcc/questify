package com.wrathbyte.questify

data class Quest(
    val id: String,
    val questID: String? = null,
    val title: String,
    val description: String? = null,
    val isCompleted: Boolean = false
)
