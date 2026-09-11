# Model Integration Guide

## Overview

This guide explains how to integrate and configure different AI models in the app.

## Supported Formats

- **ONNX Format** (.onnx) - Recommended
- **TensorFlow Lite** (.tflite)
- **GGML Format** (.ggml)

## Adding a New Model

### Step 1: Download Model

Download from HuggingFace:
```bash
huggingface-cli download model-id --local-dir ./models/model-name
```

### Step 2: Convert to ONNX (if needed)

```python
from transformers import AutoTokenizer, AutoModelForCausalLM
import torch
import onnx

model = AutoModelForCausalLM.from_pretrained("model-id")
tokenizer = AutoTokenizer.from_pretrained("model-id")

# Export to ONNX
dummy_input = tokenizer.encode("Hello", return_tensors="pt")
torch.onnx.export(model, dummy_input, "model.onnx")
```

### Step 3: Optimize Model

Use ONNX Runtime quantization:
```python
from onnxruntime.quantization import quantize_dynamic, QuantType

quantize_dynamic(
    "model.onnx",
    "model-quantized.onnx",
    weight_type=QuantType.QUInt8
)
```

### Step 4: Place in App

```
/sdcard/AI_Assistant/models/model-name/model.onnx
```

### Step 5: Update Configuration

Edit `ModelConfig.kt`:
```kotlin
val modelConfigs = mapOf(
    "path/to/model" to ModelProperties(
        name = "Model Name",
        maxTokens = 512,
        temperature = 0.7f,
        topP = 0.9f
    )
)
```

## Performance Optimization

### Quantization
Reduce model size by 4x with minimal quality loss:
```kotlin
// INT8 quantization in ONNX Runtime
```

### GPU Acceleration
Enable GPU inference on supported devices:
```kotlin
val sessionOptions = OrtSession.SessionOptions()
sessionOptions.addCUDAExecutionProvider(0) // GPU device 0
```

### Batching
Process multiple queries efficiently

## Memory Management

- Load models on-demand
- Cache tokenizers
- Clear inference cache after each query

## Monitoring

Track inference time and memory usage:
```kotlin
val startTime = System.currentTimeMillis()
val result = ortSession.run(inputs)
val inferenceTime = System.currentTimeMillis() - startTime
Log.d("Inference", "Time: ${inferenceTime}ms")
```
