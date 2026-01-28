#!/bin/bash

# Script para crear issues de GitHub basados en REQUIREMENTS.md
# Ejecutar en tu terminal local después de: gh auth login

# No usar set -e para continuar aunque haya errores de conexión

echo "🚀 Creando issues de GitHub..."
echo "Nota: Si hay errores de conexión, ejecuta este script en tu terminal local"
echo ""

# Verificar/crear label feature
echo "📋 Verificando label 'feature'..."
gh label create "feature" --description "Nueva funcionalidad o característica" --color "0E8A16" 2>/dev/null || echo "  ✓ Label 'feature' ya existe"
echo ""

# Issue 1: Consultas de conocimiento experto
echo "1️⃣ Creando issue: Consultas de conocimiento experto..."
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
echo "  ✅ Creado"
echo ""

# Issue 2: Chat inteligente con catálogo de TMDB
echo "2️⃣ Creando issue: Chat inteligente con catálogo de TMDB..."
gh issue create --title "Feature: Chat inteligente con catálogo de TMDB" \
  --body "## Scenario: Recomendación de películas por tópico

**Given** que el usuario está en la pantalla del chat

**When** el usuario escribe \"Recomiéndame 3 películas de ciencia ficción similares a Interstellar\"

**Then:**
- Gemini ejecuta la función interna \"fetchTMDBData\"
- El chat visualiza una lista de películas con títulos y calificaciones reales de TMDB
- La respuesta no contiene información inventada (hallucinations)" \
  --label "feature"
echo "  ✅ Creado"
echo ""

# Issue 3: Navegación asistida por IA
echo "3️⃣ Creando issue: Navegación asistida por IA..."
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
echo "  ✅ Creado"
echo ""

# Issue 4: Ejecución de acciones dentro de la app
echo "4️⃣ Creando issue: Ejecución de acciones dentro de la app..."
gh issue create --title "Feature: Ejecución de acciones dentro de la app" \
  --body "## Scenario: Agregar película a favoritos por comando de voz o texto

**Given** que el usuario está visualizando una recomendación en el chat

**When** el usuario escribe \"Guarda esta película en mi lista de favoritos\"

**Then:**
- Gemini identifica la intención \"ADD_FAVORITE\" y el ID de la película
- La app ejecuta el método \"saveFavorite()\" del repositorio local
- El chat confirma la acción con el mensaje \"¡Listo! He añadido la película a tus favoritos.\"" \
  --label "feature"
echo "  ✅ Creado"
echo ""

# Issue 5: Seguridad de la infraestructura de IA
echo "5️⃣ Creando issue: Seguridad de la infraestructura de IA..."
gh issue create --title "Feature: Seguridad de la infraestructura de IA" \
  --body "## Scenario: Validación de dispositivo legítimo

**Given** que un cliente externo intenta realizar una petición al SDK de Vertex AI

**But** el dispositivo no cuenta con un token válido de Firebase App Check

**Then:**
- El backend de Google Cloud rechaza la conexión
- No se consumen tokens del presupuesto del proyecto" \
  --label "feature"
echo "  ✅ Creado"
echo ""

echo "🎉 ¡Todos los issues han sido creados exitosamente!"
echo ""
echo "Para ver los issues creados, ejecuta: gh issue list"
