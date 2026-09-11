package com.ai.assistant.mobile.config

object ModelConfig {
    const val MODELS_DIR = "/sdcard/AI_Assistant/models/"
    const val CACHE_DIR = "/sdcard/AI_Assistant/cache/"
    const val MAX_CONTEXT_LENGTH = 2048
    const val DEFAULT_TEMPERATURE = 0.7f
    const val DEFAULT_TOP_P = 0.9f
    const val DEFAULT_MAX_TOKENS = 512

    // Model-specific configurations
    val modelConfigs = mapOf(
        "Qwen/Qwen2-7B-Instruct" to ModelProperties(
            name = "Qwen 3 (8B)",
            maxTokens = 512,
            temperature = 0.7f,
            topP = 0.9f
        ),
        "Qwen/Qwen2-27B-Instruct" to ModelProperties(
            name = "Qwen 3 (27B)",
            maxTokens = 1024,
            temperature = 0.7f,
            topP = 0.95f
        ),
        "moonshine-ai/kimi-k3" to ModelProperties(
            name = "Kimi K3",
            maxTokens = 512,
            temperature = 0.6f,
            topP = 0.9f
        ),
        "xAI/Grok-1" to ModelProperties(
            name = "SpaceX Grok",
            maxTokens = 768,
            temperature = 0.8f,
            topP = 0.95f
        )
    )
}

data class ModelProperties(
    val name: String,
    val maxTokens: Int,
    val temperature: Float,
    val topP: Float
)
