package com.juanpineda.mymovies.ui.chat

/**
 * MVP local-RAG interface.
 *
 * Replace the implementation with a Vertex AI Search + Gemini based answerer later,
 * keeping the same contract to satisfy "answers only from the document + citations".
 */
fun interface KnowledgeAnswerer {
    suspend fun answer(question: String): KnowledgeAnswer
}

data class KnowledgeAnswer(
    val text: String,
    val citations: List<Citation>
)

data class Citation(
    val display: String
)

data class ChatMessage(
    val role: ChatRole,
    val text: String,
    val citations: List<Citation>
)

enum class ChatRole(val label: String) {
    User("You"),
    Assistant("Assistant")
}

