package com.example.firstxmlprojectainotepad.networking.Data_model

import kotlinx.serialization.Serializable

@Serializable
data class ChatChoice(
    val message: ChatMessage
)

@Serializable
data class ChatResponse(
    val choices: List<ChatChoice> = emptyList()
)
