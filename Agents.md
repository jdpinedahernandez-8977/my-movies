# Agents.md

## Resumen del proyecto
`MyMovies` es una app Android de películas basada en TMDB, con arquitectura modular y tests. Este repo se está extendiendo con capacidades de IA (chat + navegación + acciones) y RAG sobre documentos privados.

## Estructura del repo (módulos)
- **`app`**: UI/Android app (`com.juanpineda.mymovies`)
- **`data`**: repositorios y fuentes de datos (local/remoto/ubicación)
- **`domain`**: modelos de dominio (p. ej. `Movie`, `MovieImage`)
- **`usecases`**: casos de uso (p. ej. `GetPopularMovies`, `GetMovieImages`, `ToggleMovieFavorite`)
- **`testShared`**: utilidades/mocks compartidos para tests

## Tecnologías confirmadas en el código (actual)
- **Lenguaje/Build**: Kotlin, Gradle (Android Gradle Plugin 8.5.2), Java 17
- **Android**: minSdk 23, target/compileSdk 34
- **UI**: Views con **ViewBinding** + **Jetpack Compose** (compose \(1.5.4\)), Material
- **DI**: **Koin** (`io.insert-koin:koin-android`)
- **Red/HTTP**: **Retrofit 2.9.0**, **OkHttp logging-interceptor 4.9.0**
- **Persistencia**: **Room 2.6.1**
- **Imágenes**: **Glide 4.16.0** y **Coil (compose)**
- **Asincronía**: Coroutines
- **Testing**: JUnit4, Mockito Kotlin, Espresso, MockWebServer, Idling Resource

## Tecnologías objetivo (IA / Cloud) según `STACK.md`
- **LLM**: Gemini (recomendado: **Gemini 1.5 Flash** por latencia/costo)
- **SDK Android**: Firebase Vertex AI SDK (para invocar Gemini desde Android cuando aplique)
- **RAG / Knowledge base**: Vertex AI Search and Conversation (Data Store con PDFs/URLs)
- **Orquestación opcional**: Firebase Cloud Functions (Node.js/TypeScript) como puente seguro
- **Seguridad anti-abuso**: Firebase **App Check**
- **Datos externos**: TMDB API vía function calling / capa interna tipo `fetchTMDBData`

## Requisitos (resumen en Features / Scenarios)

### Feature: Consultas de conocimiento experto (RAG)
- **Objetivo**: responder preguntas **solo** con evidencia de documentos privados (PDF) cargados en Vertex AI Search.
- **Scenario**: “¿Cuál es la política de tratamiento de datos?” → consultar Data Store → responder con **citas/referencias** al PDF.

### Feature: Chat inteligente con catálogo de TMDB
- **Objetivo**: recomendaciones con datos reales (sin “hallucinations”).
- **Scenario**: pedir “3 películas sci‑fi similares a Interstellar” → invocar `fetchTMDBData` → mostrar lista con títulos y calificaciones reales.

### Feature: Navegación asistida por IA
- **Objetivo**: deep links desde el chat hacia pantallas de la app.
- **Scenario**: recomendación incluye `mymovies://detail/27205` → tap “Ver Detalles” → abrir `MovieDetailActivity` con ese ID.

### Feature: Ejecución de acciones dentro de la app
- **Objetivo**: comandos (voz/texto) que ejecuten acciones locales (p. ej. favoritos) con confirmación en el chat.
- **Scenario**: “Guarda esta película…” → intención `ADD_FAVORITE` + ID → ejecutar `saveFavorite()` → confirmar éxito.

### Feature: Seguridad de la infraestructura de IA
- **Objetivo**: bloquear clientes no legítimos para proteger presupuesto/credenciales.
- **Scenario**: request sin token válido de App Check → rechazar en backend Google Cloud → no consumir tokens/costo.

