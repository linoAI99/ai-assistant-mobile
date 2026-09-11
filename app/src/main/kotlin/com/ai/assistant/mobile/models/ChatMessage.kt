package com.ai.assistant.mobile.models

data class ChatMessage(
    val id: String = java.util.UUID.randomUUID().toString(),
    val text: String,
    val isUser: Boolean,
    val model: String,
    val timestamp: Long = System.currentTimeMillis()
)
