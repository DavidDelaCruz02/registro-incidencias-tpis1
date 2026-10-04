# RegistroIncidencias

## Propósito del proyecto

RegistroIncidencias es una aplicación móvil desarrollada para permitir el registro y consulta de incidencias de manera organizada.

El proyecto se desarrolla de forma incremental, incorporando funcionalidades de interacción y validación básica antes de implementar el almacenamiento permanente de información.

## Tecnologías utilizadas

- Android Studio
- Kotlin
- Jetpack Compose
- Gradle
- Git
- GitHub

## Funcionalidades actuales

- Pantalla inicial para el registro de incidencias.
- Campo para ingresar el título.
- Campo para ingresar la descripción.
- Configuración contextual del teclado para facilitar la entrada de texto.
- Selección de prioridad: Baja, Media y Alta.
- Interacción táctil mediante selección de prioridad.
- Botón "Crear reporte".
- Retroalimentación visible después de crear un reporte.
- Ejecución y prueba en un dispositivo físico.

## Avance - Semana 4

Durante esta semana se mejoró la interacción de la aplicación mediante la configuración del teclado y la incorporación de una selección de prioridad.

### Cambios realizados

- Se configuró el teclado del campo de título utilizando `KeyboardOptions`.
- Se estableció la capitalización de oraciones para facilitar la escritura.
- Se configuró la acción `ImeAction.Next` para mejorar el flujo de entrada.
- Se incorporó la selección de prioridad mediante interacción táctil.
- Se agregaron las opciones Baja, Media y Alta.
- Se implementó retroalimentación visible mostrando la prioridad seleccionada.
- Se probó la aplicación en un dispositivo físico.

### Estado actual

La aplicación permite ingresar el título y la descripción de una incidencia, seleccionar una prioridad y crear un reporte simulado mostrando una retroalimentación visible.

La información todavía no se almacena en una base de datos.

## Ejecución

El proyecto puede ejecutarse desde Android Studio utilizando un emulador Android o un dispositivo físico.

## Estructura principal

```text
RegistroIncidencias
├── app
│   ├── src
│   │   └── main
│   │       ├── java
│   │       ├── res
│   │       └── AndroidManifest.xml
│   └── build.gradle.kts
├── gradle
├── build.gradle.kts
├── settings.gradle.kts
└── README.md
```
## Autor

David De la Cruz
