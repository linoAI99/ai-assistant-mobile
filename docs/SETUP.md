# Setup Guide

## Prerequisites

- Android Studio Giraffe (2022.3.1) or later
- Android NDK
- Gradle 8.1+
- JDK 17+
- Minimum 4GB RAM
- 10GB free storage space

## Installation Steps

### 1. Clone the Repository

```bash
git clone https://github.com/linoAI99/ai-assistant-mobile.git
cd ai-assistant-mobile
```

### 2. Install Dependencies

```bash
./gradlew clean build
```

### 3. Download AI Models

Models should be downloaded from HuggingFace:

- **Qwen 3 (8B)**: https://huggingface.co/Qwen/Qwen2-7B-Instruct
- **Qwen 3 (27B)**: https://huggingface.co/Qwen/Qwen2-27B-Instruct
- **Kimi K3**: https://huggingface.co/moonshine-ai/kimi-k3
- **SpaceX Grok**: https://huggingface.co/xAI/Grok-1

### 4. Place Models

Create directories:
```bash
mkdir -p /sdcard/AI_Assistant/models/
mkdir -p /sdcard/AI_Assistant/cache/
```

Place ONNX/TFLite converted models in the models directory.

### 5. Build APK

```bash
./gradlew assembleRelease
```

### 6. Install APK

```bash
adb install -r app/build/outputs/apk/release/app-release.apk
```

## Configuration

Edit `app/src/main/kotlin/com/ai/assistant/mobile/config/ModelConfig.kt` to customize model settings.

## Device Compatibility

- **Infinix Smart 8**: Recommended (4GB RAM, 128GB Storage)
- **Minimum**: Android 8.0+ (API 26)
- **Optimal**: Android 12+ with 6GB+ RAM

## Troubleshooting

### Out of Memory Issues
Reduce model size or context length in ModelConfig.kt

### Slow Inference
Enable GPU acceleration or use quantized models

### Model Loading Fails
Ensure models are in correct format (ONNX/TFLite) and paths are correct
