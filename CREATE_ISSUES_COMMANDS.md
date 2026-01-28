# Comandos para crear issues de GitHub

Ejecuta estos comandos uno por uno en tu terminal después de autenticarte con `gh auth login`:

## 1. Consultas de conocimiento experto
```bash
gh issue create --title "Feature: Consultas de conocimiento experto" \
  --body "## Background
- El administrador ha cargado el PDF \"Politicas_Privacidad_2024.pdf\" en Vertex AI Search
- El usuario ha iniciado sesión en la aplicación MyMovies

## Scenario: Responder preguntas basadas en documentos privados

**When** el usuario pregunta \"¿Cuál es la política de tratamiento de datos?\"

**Then:**
- El modelo Gemini consulta el Data Store de Vertex AI
- El chat muestra una respuesta basada exclusivamente en el contenido del PDF
- El mensaje incluye una cita o referencia a la fuente del documento" \
  --label "feature"
```

## 2. Chat inteligente con catálogo de TMDB
```bash
gh issue create --title "Feature: Chat inteligente con catálogo de TMDB" \
  --body "## Scenario: Recomendación de películas por tópico

**Given** que el usuario está en la pantalla del chat

**When** el usuario escribe \"Recomiéndame 3 películas de ciencia ficción similares a Interstellar\"

**Then:**
- Gemini ejecuta la función interna \"fetchTMDBData\"
- El chat visualiza una lista de películas con títulos y calificaciones reales de TMDB
- La respuesta no contiene información inventada (hallucinations)" \
  --label "feature"
```

## 3. Navegación asistida por IA
```bash
gh issue create --title "Feature: Navegación asistida por IA" \
  --body "## Scenario: Redirección al detalle de una película sugerida

**Given:**
- El chat ha recomendado la película \"Inception\" con ID \"27205\"
- El mensaje contiene un botón de acción con el link \"mymovies://detail/27205\"

**When** el usuario presiona el botón \"Ver Detalles\"

**Then:**
- La aplicación intercepta el Intent de navegación
- Se abre la pantalla MovieDetailActivity mostrando la información de \"Inception\"" \
  --label "feature"
```

## 4. Ejecución de acciones dentro de la app
```bash
gh issue create --title "Feature: Ejecución de acciones dentro de la app" \
  --body "## Scenario: Agregar película a favoritos por comando de voz o texto

**Given** que el usuario está visualizando una recomendación en el chat

**When** el usuario escribe \"Guarda esta película en mi lista de favoritos\"

**Then:**
- Gemini identifica la intención \"ADD_FAVORITE\" y el ID de la película
- La app ejecuta el método \"saveFavorite()\" del repositorio local
- El chat confirma la acción con el mensaje \"¡Listo! He añadido la película a tus favoritos.\"" \
  --label "feature"
```

## 5. Seguridad de la infraestructura de IA
```bash
gh issue create --title "Feature: Seguridad de la infraestructura de IA" \
  --body "## Scenario: Validación de dispositivo legítimo

**Given** que un cliente externo intenta realizar una petición al SDK de Vertex AI

**But** el dispositivo no cuenta con un token válido de Firebase App Check

**Then:**
- El backend de Google Cloud rechaza la conexión
- No se consumen tokens del presupuesto del proyecto" \
  --label "feature"
```
