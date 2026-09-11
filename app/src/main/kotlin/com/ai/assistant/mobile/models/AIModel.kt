package com.ai.assistant.mobile.models

enum class AIModel(
    val displayName: String,
    val modelId: String,
    val description: String,
    val size: String,
    val requiredRam: String
) {
    QWEN_8B(
        displayName = "Qwen 3 (8B)",
        modelId = "Qwen/Qwen2-7B-Instruct",
        description = "Lightweight model for fast inference",
        size = "8GB",
        requiredRam = "4GB"
    ),
    QWEN_27B(
        displayName = "Qwen 3 (27B Max)",
        modelId = "Qwen/Qwen2-27B-Instruct",
        description = "Advanced model with better capabilities",
        size = "27GB",
        requiredRam = "16GB"
    ),
    KIMI_K3(
        displayName = "Kimi K3",
        modelId = "moonshine-ai/kimi-k3",
        description = "Optimized for mobile devices",
        size = "10GB",
        requiredRam = "6GB"
    ),
    GROK_AI(
        displayName = "SpaceX Grok",
        modelId = "xAI/Grok-1",
        description = "Latest open-source alternative",
        size = "12GB",
        requiredRam = "8GB"
    )
}
