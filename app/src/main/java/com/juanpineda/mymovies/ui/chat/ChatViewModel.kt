package com.juanpineda.mymovies.ui.chat

import com.juanpineda.mymovies.ui.common.ScopedViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ChatViewModel(
    private val answerer: KnowledgeAnswerer,
    uiDispatcher: CoroutineDispatcher
) : ScopedViewModel(uiDispatcher) {

    private val _messages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val messages: StateFlow<List<ChatMessage>> = _messages.asStateFlow()

    init {
        initScope()
        _messages.value = listOf(
            ChatMessage(
                role = ChatRole.Assistant,
                text = "Ask me about the privacy policy. I will answer using only the document.",
                citations = emptyList()
            )
        )
    }

    fun ask(question: String) {
        _messages.value = _messages.value + ChatMessage(ChatRole.User, question, emptyList())
        launch {
            val answer = answerer.answer(question)
            _messages.value = _messages.value + ChatMessage(
                role = ChatRole.Assistant,
                text = answer.text,
                citations = answer.citations
            )
        }
    }

    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }
}

