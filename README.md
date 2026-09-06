# RegistroIncidencias

## Propósito del proyecto

RegistroIncidencias es una aplicación móvil desarrollada para permitir el registro y consulta de incidencias de manera organizada.

El proyecto se encuentra actualmente en una etapa inicial de desarrollo. La aplicación presenta una interfaz para ingresar información básica de una incidencia y servirá como base para implementar posteriormente las funcionalidades de almacenamiento y consulta.

## Tecnologías y herramientas utilizadas

- Android Studio
- Kotlin
- Jetpack Compose
- Gradle
- Git
- GitHub

## Estado actual del proyecto

### Funcionalidades implementadas

- Pantalla inicial de la aplicación.
- Título y descripción relacionados con el registro de incidencias.
- Campos de entrada para el título y la descripción de una incidencia.
- Estado básico utilizando `remember` y `mutableStateOf`.
- Botón "Crear reporte".
- Retroalimentación visible después de crear un reporte.
- Personalización de textos y colores de la interfaz.
- Ejecución y prueba de la aplicación en un dispositivo físico.

### Funcionalidades pendientes

- Implementar el almacenamiento de información.
- Implementar la consulta de incidencias.
- Desarrollar las siguientes pantallas y funcionalidades del sistema.
- Incorporar persistencia de datos mediante una base de datos.

## Avance - Semana 3

Se mejoró la pantalla inicial de RegistroIncidencias para permitir la entrada básica de información por parte del usuario.

### Cambios realizados

- Se agregó un campo para el título de la incidencia.
- Se agregó un campo para la descripción.
- Se implementó estado básico utilizando `remember` y `mutableStateOf`.
- Se agregó el botón "Crear reporte".
- Se agregó retroalimentación visible al crear un reporte.
- Se actualizó la interfaz para facilitar el ingreso de información.
- La aplicación fue probada en un dispositivo físico.

### Estado actual

La aplicación permite ingresar un título y una descripción y muestra una retroalimentación inicial al presionar "Crear reporte". La información todavía no se almacena en una base de datos.

## Cómo abrir el proyecto

1. Instalar Android Studio.
2. Clonar o descargar el repositorio desde GitHub.
3. Abrir Android Studio.
4. Seleccionar la carpeta del proyecto `RegistroIncidencias`.
5. Esperar a que Android Studio sincronice el proyecto con Gradle.
6. Ejecutar la aplicación utilizando un dispositivo físico o un emulador Android.

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
