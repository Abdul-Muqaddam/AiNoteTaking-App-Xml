package com.example.firstxmlprojectainotepad.networking

import android.util.Log
import com.example.firstxmlprojectainotepad.networking.Data_model.ChatMessage
import com.example.firstxmlprojectainotepad.networking.Data_model.ChatRequest
import com.example.firstxmlprojectainotepad.networking.Data_model.ChatResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.headers
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpHeaders
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.json.Json

suspend fun sendMessageToChatGPT(client: HttpClient, prompt: String, apiKey: String): String {
    val request = ChatRequest(
        model = "gpt-4o",
        temperature =  0.9,
        max_tokens =  100,
        messages = listOf(
            ChatMessage(role = "user", content = prompt)
        )
    )

//    val raw = client.post("https://cheapest-gpt-4-turbo-gpt-4-vision-chatgpt-openai-ai-api.p.rapidapi.com/v1/chat/completions") {
//        headers {
//            append("x-rapidapi-key",  apiKey)
//            append("x-rapidapi-host", "cheapest-gpt-4-turbo-gpt-4-vision-chatgpt-openai-ai-api.p.rapidapi.com")
//            append(HttpHeaders.Accept, "application/json")
//        }
//        contentType(ContentType.Application.Json)
//        setBody(request)
//    }.bodyAsText()
//
//    Log.d("RAW_API_RESPONSE", raw)

    val response: ChatResponse = client.post("https://cheapest-gpt-4-turbo-gpt-4-vision-chatgpt-openai-ai-api.p.rapidapi.com/v1/chat/completions") {
        headers {
            append("x-rapidapi-key",  apiKey)
            append("x-rapidapi-host", "cheapest-gpt-4-turbo-gpt-4-vision-chatgpt-openai-ai-api.p.rapidapi.com")
            append(HttpHeaders.Accept, "application/json")
        }
        contentType(ContentType.Application.Json)
        setBody(request) // ✅ critical part
    }.body()

    Log.d("API_RESPONSE", response.toString())

    Log.d("REQ", Json.encodeToString(ChatRequest.serializer(), request))




    return response.choices.firstOrNull()?.message?.content ?: "No response"


//    return "null"
}
