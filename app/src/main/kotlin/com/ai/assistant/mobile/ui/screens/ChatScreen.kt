package com.ai.assistant.mobile.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ai.assistant.mobile.models.ChatMessage
import com.ai.assistant.mobile.models.AIModel

@Composable
fun ChatScreen() {
    var messages by remember { mutableStateOf(listOf<ChatMessage>()) }
    var inputText by remember { mutableStateOf(TextFieldValue("")) }
    var selectedModel by remember { mutableStateOf(AIModel.QWEN_8B) }
    var isLoading by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0A0E27))
    ) {
        // Header with Model Selector
        ModelSelectorBar(
            selectedModel = selectedModel,
            onModelChanged = { selectedModel = it }
        )

        Divider(color = Color(0xFF1A1F3A), thickness = 1.dp)

        // Chat Messages
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(8.dp),
            reverseLayout = true
        ) {
            items(messages.reversed()) { message ->
                ChatMessageBubble(message = message)
            }
        }

        Divider(color = Color(0xFF1A1F3A), thickness = 1.dp)

        // Input Area
        ChatInputField(
            value = inputText,
            onValueChange = { inputText = it },
            onSend = {
                if (inputText.text.isNotBlank()) {
                    messages = messages + ChatMessage(
                        text = inputText.text,
                        isUser = true,
                        model = selectedModel.displayName
                    )
                    inputText = TextFieldValue("")
                    isLoading = true
                    // Simulate AI response
                    messages = messages + ChatMessage(
                        text = "Processing your message with $selectedModel...",
                        isUser = false,
                        model = selectedModel.displayName
                    )
                    isLoading = false
                }
            },
            isLoading = isLoading
        )
    }
}

@Composable
fun ModelSelectorBar(
    selectedModel: AIModel,
    onModelChanged: (AIModel) -> Unit
) {
    var expandedMenu by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "AI Assistant",
            color = Color(0xFF00D9FF),
            fontSize = 20.sp,
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
        )

        Box {
            Button(
                onClick = { expandedMenu = true },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF1A1F3A)
                ),
                modifier = Modifier.height(40.dp)
            ) {
                Text(
                    text = selectedModel.displayName,
                    color = Color(0xFF00D9FF),
                    fontSize = 12.sp
                )
            }

            DropdownMenu(
                expanded = expandedMenu,
                onDismissRequest = { expandedMenu = false },
                modifier = Modifier.background(Color(0xFF1A1F3A))
            ) {
                AIModel.values().forEach { model ->
                    DropdownMenuItem(
                        text = { Text(model.displayName, color = Color.White) },
                        onClick = {
                            onModelChanged(model)
                            expandedMenu = false
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun ChatMessageBubble(message: ChatMessage) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = if (message.isUser) Arrangement.End else Arrangement.Start
    ) {
        Surface(
            modifier = Modifier
                .widthIn(max = 300.dp)
                .padding(4.dp),
            shape = RoundedCornerShape(12.dp),
            color = if (message.isUser) Color(0xFF00D9FF) else Color(0xFF1A1F3A),
            tonalElevation = 4.dp
        ) {
            Column(
                modifier = Modifier.padding(12.dp)
            ) {
                Text(
                    text = message.text,
                    color = if (message.isUser) Color.Black else Color.White,
                    fontSize = 14.sp
                )
                Text(
                    text = message.model,
                    color = if (message.isUser) Color.Black else Color(0xFF00D9FF),
                    fontSize = 10.sp,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}

@Composable
fun ChatInputField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    onSend: () -> Unit,
    isLoading: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .weight(1f)
                .height(48.dp),
            placeholder = { Text("Type your message...", color = Color.Gray) },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFF1A1F3A),
                unfocusedContainerColor = Color(0xFF1A1F3A),
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedIndicatorColor = Color(0xFF00D9FF),
                unfocusedIndicatorColor = Color(0xFF2A2F4A)
            ),
            shape = RoundedCornerShape(8.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Button(
            onClick = onSend,
            enabled = !isLoading,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF00D9FF)
            ),
            modifier = Modifier
                .height(48.dp)
                .width(48.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(if (isLoading) "..." else "Send", color = Color.Black)
        }
    }
}
