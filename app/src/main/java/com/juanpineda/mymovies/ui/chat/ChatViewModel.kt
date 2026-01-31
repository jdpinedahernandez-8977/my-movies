package com.juanpineda.mymovies.ui.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.ai.FirebaseAI
import com.google.firebase.ai.GenerativeModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class ChatMessage(
    val text: String,
    val isUser: Boolean
)

class ChatViewModel : ViewModel() {
    private val _messages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val messages: StateFlow<List<ChatMessage>> = _messages

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val generativeModel: GenerativeModel = FirebaseAI.instance.generativeModel("gemini-2.5-flash-lite")
    private val chat = generativeModel.startChat()

    fun sendMessage(text: String) {
        viewModelScope.launch {
            _messages.value = _messages.value + ChatMessage(text, true)
            _isLoading.value = true

            try {
                val response = chat.sendMessage(text)
                val aiResponse = response.text ?: "No response"
                _messages.value = _messages.value + ChatMessage(aiResponse, false)
            } catch (e: Exception) {
                _messages.value = _messages.value + ChatMessage("Error: ${e.message}", false)
            } finally {
                _isLoading.value = false
            }
        }
    }
}
