# RegistroIncidencias

## Propósito del proyecto

RegistroIncidencias es una aplicación móvil desarrollada para facilitar el
registro y posterior consulta de incidencias relacionadas con sistemas
informáticos de manera organizada.

Actualmente el proyecto se encuentra en una etapa inicial de desarrollo y
cuenta con una pantalla funcional para ingresar y preparar información básica
de una incidencia.

## Tecnologías utilizadas

- Android Studio
- Kotlin
- Jetpack Compose
- Gradle
- Git y GitHub

## Funcionalidades actuales

- Pantalla inicial de RegistroIncidencias.
- Campo para ingresar el título de una incidencia.
- Campo para ingresar una descripción breve.
- Estado básico mediante `remember` y `mutableStateOf`.
- Botón "Crear reporte".
- Retroalimentación visible al preparar un reporte.
- Validación básica cuando no se ingresa un título.
- Ejecución y prueba en un dispositivo físico.

## Requerimientos iniciales

### Funcionales

- Permitir al usuario ingresar el título de una incidencia.
- Permitir al usuario ingresar una descripción breve.

### Interfaz y uso

- Presentar campos claramente identificados para facilitar el ingreso de
  información.
- Mostrar una retroalimentación visible al presionar "Crear reporte".

## Alcance actual

La aplicación permite ingresar información básica y preparar visualmente un
reporte. En esta etapa los datos no se almacenan de forma permanente.

Como parte del desarrollo posterior se implementará el almacenamiento y
consulta de incidencias, además de nuevas funcionalidades y pantallas.

## Ejecución

El proyecto puede ejecutarse desde Android Studio utilizando un emulador
Android o un dispositivo físico.

## Estructura principal

```text
RegistroIncidencias
├── app
│   ├── src
│   │   └── main
│   │       ├── java
│   │       ├── res
│   │       └── AndroidManifest.xml
│   ├── build.gradle.kts
├── gradle
├── build.gradle.kts
├── settings.gradle.kts
└── README.md
```
## Autor

David De la Cruz
