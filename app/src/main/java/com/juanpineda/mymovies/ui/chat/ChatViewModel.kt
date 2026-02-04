package com.juanpineda.mymovies.ui.chat

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.ai.FirebaseAI
import com.google.firebase.ai.GenerativeModel
import com.google.firebase.ai.type.generationConfig
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class ChatMessage(
    val text: String,
    val isUser: Boolean
)

class ChatViewModel(private val context: Context) : ViewModel() {
    private val _messages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val messages: StateFlow<List<ChatMessage>> = _messages

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    val config = generationConfig {
        // Aumenta este valor según la capacidad del modelo
        // Para Gemini Flash suele soportar hasta 8,192 o más
        maxOutputTokens = 4096
        temperature = 0.7f // Una temperatura ligeramente más alta ayuda a la fluidez
    }
    private val generativeModel: GenerativeModel = FirebaseAI.instance.generativeModel(
        modelName = "gemini-2.5-flash-lite",
        generationConfig = config
    )
    
    private val chat = generativeModel.startChat()

    private fun loadAssetFile(fileName: String): String {
        return try {
            context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (e: Exception) {
            ""
        }
    }

    init {
        viewModelScope.launch {
            val mdFiles = context.assets.list("")?.filter { it.endsWith(".md") } ?: emptyList()
            val allDocs = mdFiles.joinToString("\n\n") { fileName ->
                loadAssetFile(fileName)
            }
            
            val systemPrompt = """
                Eres un asistente virtual de atención al cliente de Itaú Uruguay.
                
                INSTRUCCIONES IMPORTANTES:
                1. Siempre responde en español uruguayo
                2. Eres amable, profesional y servicial
                3. Tu objetivo es ayudar a los clientes con consultas sobre servicios bancarios de Itaú Uruguay
                4. Temas principales que manejas:
                   - Usuario Itaú (registro, acceso, contraseñas)
                   - Débitos Automáticos
                   - Fonasa / BPS
                   - Transferencias
                   - Préstamos
                   - Banca móvil y online
                   - Inversiones
                   - Seguros
                   - Cuentas
                   - Tarjetas de Crédito
                   - Tarjetas de Débito
                   - Programa Volar
                   - LATAM Pass
                   - Seguridad e iToken
                   - Beneficios
                   - Itaú Personal Bank
                   - Gestiones BPS
                5. Si no tienes información específica, sugiere contactar con un ejecutivo
                6. Mantén respuestas concisas y claras
                7. Siempre ofrece ayuda adicional al finalizar
                
                CONOCIMIENTO BASE:
                
                $allDocs
                
                Ejemplo de respuesta:
                "¡Hola! Soy tu asistente virtual de Itaú Uruguay. ¿En qué puedo ayudarte hoy?"
            """.trimIndent()
            
            try {
                chat.sendMessage(systemPrompt)
            } catch (e: Exception) {
                // Ignore initialization error
            }
        }
    }

    fun sendMessage(text: String) {
        viewModelScope.launch {
            _messages.value = _messages.value + ChatMessage(text, true)
            _isLoading.value = true

            try {
                val response = chat.sendMessage(text)
                val aiResponse = response.text ?: "Lo siento, no pude procesar tu consulta. Por favor, intenta nuevamente."
                
                android.util.Log.d("ChatViewModel", "User: $text")
                android.util.Log.d("ChatViewModel", "AI Response: $aiResponse")
                
                _messages.value = _messages.value + ChatMessage(aiResponse, false)
            } catch (e: Exception) {
                android.util.Log.e("ChatViewModel", "Error: ${e.message}", e)
                _messages.value = _messages.value + ChatMessage(
                    "Disculpa, ocurrió un error. Por favor contacta con un ejecutivo de Itaú.",
                    false
                )
            } finally {
                _isLoading.value = false
            }
        }
    }


}
