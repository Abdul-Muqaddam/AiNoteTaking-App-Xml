package com.example.firstxmlprojectainotepad.networking.Data_model

import kotlinx.serialization.Serializable

@Serializable
data class ChatMessage(
    val role: String,
    val content: String
)

@Serializable
data class ChatRequest(
    val model: String ,
    val messages: List<ChatMessage>,
    val max_tokens: Int ,
    val temperature: Double
)
