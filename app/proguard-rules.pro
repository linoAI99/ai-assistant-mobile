# Keep ONNX Runtime classes
-keep class com.microsoft.onnxruntime.** { *; }
-keep class com.microsoft.onnxruntime.**$* { *; }

# Keep TensorFlow Lite classes
-keep class org.tensorflow.lite.** { *; }
-keep class org.tensorflow.lite.**$* { *; }

# Keep Compose classes
-keep class androidx.compose.** { *; }
-keep class androidx.compose.**$* { *; }

# Keep Android classes
-keep class androidx.** { *; }
-keep class android.** { *; }

# Keep data classes
-keepclassmembers class com.ai.assistant.mobile.models.** {
    public synthetic <methods>;
}

# Keep application classes
-keep class com.ai.assistant.mobile.** { *; }

# Optimization settings
-optimizationpasses 5
-dontusemixedcaseclassnames
-verbose

# Remove logging
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
    public static *** i(...);
}
