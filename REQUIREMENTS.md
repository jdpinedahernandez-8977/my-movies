Feature: Consultas de conocimiento experto
  
  Background:
    Given que el administrador ha cargado el PDF "Politicas_Privacidad_2024.pdf" en Vertex AI Search
    And el usuario ha iniciado sesión en la aplicación MyMovies

  Scenario: Responder preguntas basadas en documentos privados
    When el usuario pregunta "¿Cuál es la política de tratamiento de datos?"
    Then el modelo Gemini consulta el Data Store de Vertex AI
    And el chat muestra una respuesta basada exclusivamente en el contenido del PDF
    And el mensaje incluye una cita o referencia a la fuente del documento

Feature: Chat inteligente con catálogo de TMDB

  Scenario: Recomendación de películas por tópico
    Given que el usuario está en la pantalla del chat
    When el usuario escribe "Recomiéndame 3 películas de ciencia ficción similares a Interstellar"
    Then Gemini ejecuta la función interna "fetchTMDBData"
    And el chat visualiza una lista de películas con títulos y calificaciones reales de TMDB
    And la respuesta no contiene información inventada (hallucinations)

Feature: Navegación asistida por IA

  Scenario: Redirección al detalle de una película sugerida
    Given que el chat ha recomendado la película "Inception" con ID "27205"
    And el mensaje contiene un botón de acción con el link "mymovies://detail/27205"
    When el usuario presiona el botón "Ver Detalles"
    Then la aplicación intercepta el Intent de navegación
    And se abre la pantalla MovieDetailActivity mostrando la información de "Inception"

Feature: Ejecución de acciones dentro de la app

  Scenario: Agregar película a favoritos por comando de voz o texto
    Given que el usuario está visualizando una recomendación en el chat
    When el usuario escribe "Guarda esta película en mi lista de favoritos"
    Then Gemini identifica la intención "ADD_FAVORITE" y el ID de la película
    And la app ejecuta el método "saveFavorite()" del repositorio local
    And el chat confirma la acción con el mensaje "¡Listo! He añadido la película a tus favoritos."

Feature: Seguridad de la infraestructura de IA

  Scenario: Validación de dispositivo legítimo
    Given que un cliente externo intenta realizar una petición al SDK de Vertex AI
    But el dispositivo no cuenta con un token válido de Firebase App Check
    Then el backend de Google Cloud rechaza la conexión
    And no se consumen tokens del presupuesto del proyecto