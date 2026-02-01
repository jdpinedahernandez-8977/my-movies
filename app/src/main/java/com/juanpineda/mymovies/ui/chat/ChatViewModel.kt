package com.juanpineda.mymovies.ui.chat

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.ai.FirebaseAI
import com.google.firebase.ai.GenerativeModel
import com.juanpineda.data.repository.MoviesRepository
import com.juanpineda.domain.Movie
import com.juanpineda.mymovies.ui.detail.DetailActivity
import com.juanpineda.usecases.SearchMovies
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class ChatMessage(
    val text: String,
    val isUser: Boolean,
    val movie: Movie? = null
)

class ChatViewModel(
    private val searchMovies: SearchMovies,
    private val moviesRepository: MoviesRepository
) : ViewModel() {
    private val _messages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val messages: StateFlow<List<ChatMessage>> = _messages

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _navigateToMovie = MutableStateFlow<Movie?>(null)
    val navigateToMovie: StateFlow<Movie?> = _navigateToMovie

    private val generativeModel: GenerativeModel = FirebaseAI.instance.generativeModel(
        modelName = "gemini-2.5-flash-lite"
    )
    
    private val chat = generativeModel.startChat()

    init {
        viewModelScope.launch {
            val systemPrompt = """
                Eres un asistente experto en películas especializado en The Movie Database (TMDb).
                
                INSTRUCCIONES IMPORTANTES:
                1. Siempre responde en español
                2. Tu especialidad es información de películas de TMDb (https://www.themoviedb.org)
                3. Cuando recibas información de películas encontradas en TMDb, SIEMPRE menciona la primera película
                4. IMPORTANTE: Cuando menciones una película que fue encontrada en TMDb, DEBES terminar tu respuesta con [VER_DETALLES:nombre]
                5. Si no hay películas encontradas en TMDb, responde con tu conocimiento general SIN incluir [VER_DETALLES]
                6. Sé conversacional, amigable y útil
                7. Puedes recomendar películas, explicar tramas, hablar sobre actores y directores
                
                FORMATO DE RESPUESTA OBLIGATORIO:
                - Si recibes "Películas encontradas en TMDb: [lista]", DEBES incluir [VER_DETALLES:nombre_primera_pelicula]
                - Ejemplo: "Inception es una película de ciencia ficción... [VER_DETALLES:Inception]"
                - Si NO recibes películas encontradas, NO incluyas [VER_DETALLES]
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
                // Extract movie name from user message using AI
                val movieName = extractMovieName(text)
                android.util.Log.d("ChatViewModel", "Extracted movie name: $movieName")
                
                // Search for movies in TMDb with extracted name
                val movies = if (movieName.isNotEmpty()) searchMovies(movieName) else emptyList()
                android.util.Log.d("ChatViewModel", "Search query: $movieName")
                android.util.Log.d("ChatViewModel", "Movies found: ${movies.size}")
                movies.forEach { movie ->
                    android.util.Log.d("ChatViewModel", "Movie: ${movie.title} (ID: ${movie.id})")
                }
                
                val movieContext = if (movies.isNotEmpty()) {
                    "\n\nPelículas encontradas en TMDb: ${movies.take(5).joinToString { "${it.title} (${it.releaseDate.take(4)})" }}"
                } else ""
                
                val response = chat.sendMessage(text + movieContext)
                val aiResponse = response.text ?: "No response"
                
                android.util.Log.d("ChatViewModel", "AI Response: $aiResponse")
                
                // Check if response contains movie reference
                val movie = extractMovieReference(aiResponse, movies)
                
                android.util.Log.d("ChatViewModel", "Extracted movie: ${movie?.title} (ID: ${movie?.id})")
                
                _messages.value = _messages.value + ChatMessage(
                    aiResponse.replace(Regex("\\[VER_DETALLES:.*?\\]"), "").trim(),
                    false,
                    movie
                )
            } catch (e: Exception) {
                android.util.Log.e("ChatViewModel", "Error: ${e.message}", e)
                _messages.value = _messages.value + ChatMessage("Error: ${e.message}", false)
            } finally {
                _isLoading.value = false
            }
        }
    }

    private suspend fun extractMovieName(text: String): String {
        return try {
            val extractionModel = FirebaseAI.instance.generativeModel(modelName = "gemini-2.5-flash-lite")
            val prompt = """Extrae SOLO el nombre de la película del siguiente texto. Si no hay ninguna película mencionada, responde con una palabra clave de búsqueda relevante o vacío.
Texto: "$text"
Respuesta (solo el nombre de la película, sin explicaciones):"""
            val response = extractionModel.generateContent(prompt)
            response.text?.trim() ?: ""
        } catch (e: Exception) {
            android.util.Log.e("ChatViewModel", "Error extracting movie name: ${e.message}")
            text
        }
    }

    private fun extractMovieReference(response: String, movies: List<Movie>): Movie? {
        // First, try to find the [VER_DETALLES:...] tag
        val regex = Regex("\\[VER_DETALLES:(.*?)\\]")
        val match = regex.find(response)
        
        if (match != null && movies.isNotEmpty()) {
            android.util.Log.d("ChatViewModel", "Found VER_DETALLES tag")
            return movies.first()
        }
        
        // If no tag found but we have movies, check if any movie title is mentioned in the response
        if (movies.isNotEmpty()) {
            for (movie in movies) {
                if (response.contains(movie.title, ignoreCase = true)) {
                    android.util.Log.d("ChatViewModel", "Found movie title '${movie.title}' in response")
                    return movie
                }
            }
            // If we found movies but none mentioned, return the first one
            android.util.Log.d("ChatViewModel", "No movie title found, returning first movie")
            return movies.first()
        }
        
        android.util.Log.d("ChatViewModel", "No movie reference found")
        return null
    }

    fun navigateToMovieDetail(context: Context, movie: Movie) {
        viewModelScope.launch {
            try {
                // Save movie to local database first
                moviesRepository.saveMovie(movie)
                android.util.Log.d("ChatViewModel", "Movie saved: ${movie.title} (ID: ${movie.id})")
                
                // Then navigate
                val intent = Intent(context, DetailActivity::class.java).apply {
                    putExtra(DetailActivity.MOVIE, movie.id)
                }
                context.startActivity(intent)
            } catch (e: Exception) {
                android.util.Log.e("ChatViewModel", "Error navigating: ${e.message}", e)
            }
        }
    }
}
