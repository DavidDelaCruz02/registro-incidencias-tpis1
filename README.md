# RegistroIncidencias

## Propósito del proyecto

RegistroIncidencias es una aplicación móvil desarrollada para permitir el registro y consulta de incidencias de manera organizada.

El proyecto se desarrolla de forma incremental, incorporando funcionalidades de interacción, validación básica e integración inicial de sensores antes de implementar el almacenamiento permanente de información.

## Tecnologías utilizadas

- Android Studio
- Kotlin
- Jetpack Compose
- Gradle
- Git
- GitHub
- SensorManager
- SensorEventListener

## Funcionalidades actuales

- Pantalla inicial para el registro de incidencias.
- Campo para ingresar el título.
- Campo para ingresar la descripción.
- Configuración contextual del teclado para facilitar la entrada de texto.
- Selección de prioridad: Baja, Media y Alta.
- Interacción táctil mediante selección de prioridad.
- Botón "Crear reporte".
- Retroalimentación visible después de crear un reporte.
- Lectura del acelerómetro del dispositivo.
- Visualización de los valores de los ejes X, Y y Z en m/s².
- Indicador interpretativo del movimiento.
- Manejo del caso en que el acelerómetro no esté disponible.
- Ejecución y prueba en un dispositivo físico.

## Avance - semana 10

Durante esta semana se mejoró la interacción de la aplicación mediante la configuración del teclado y la incorporación de una selección de prioridad.

### Cambios realizados

- Se configuró el teclado del campo de título utilizando `KeyboardOptions`.
- Se estableció la capitalización de oraciones para facilitar la escritura.
- Se configuró la acción `ImeAction.Next` para mejorar el flujo de entrada.
- Se incorporó la selección de prioridad mediante interacción táctil.
- Se agregaron las opciones Baja, Media y Alta.
- Se implementó retroalimentación visible mostrando la prioridad seleccionada.
- Se probó la aplicación en un dispositivo físico.

### Estado de esa versión

La aplicación permitía ingresar el título y la descripción de una incidencia, seleccionar una prioridad y crear un reporte simulado mostrando retroalimentación visible.

La información todavía no se almacena en una base de datos.

## Avance actual - Integración del acelerómetro

### Objetivo

Integrar el acelerómetro del dispositivo móvil para obtener lecturas relacionadas con la aceleración y mostrar información del sensor dentro de la aplicación.

### Cambios realizados

- Se obtuvo acceso al servicio de sensores mediante `SensorManager`.
- Se identificó el acelerómetro mediante `Sensor.TYPE_ACCELEROMETER`.
- Se implementó `SensorEventListener` para recibir las lecturas del sensor.
- Se muestran los valores de los ejes X, Y y Z en m/s².
- Se actualizaron los valores visibles mediante estados de Jetpack Compose.
- Se incorporó una interpretación aproximada del movimiento.
- Se contempló el caso en que el sensor no esté disponible.
- Se utilizó `DisposableEffect` para gestionar el registro y la liberación del listener.

### Pruebas realizadas

La aplicación se ejecutó en un dispositivo físico. Se comprobó que los valores de los ejes cambian al mover e inclinar suavemente el teléfono.

También se verificó que el formulario de incidencias, la selección de prioridad y el botón para crear reportes siguen funcionando.

### Justificación del sensor

Se seleccionó el acelerómetro porque permite medir la aceleración en tres ejes del dispositivo. Su integración constituye una primera demostración de cómo la información de los sensores puede complementar una aplicación móvil de registro de incidencias.

### Limitaciones

Las lecturas del acelerómetro también incluyen el efecto de la gravedad. Por ello, el indicador de movimiento es aproximado y no permite confirmar por sí solo que haya ocurrido una incidencia real.

La disponibilidad y las lecturas del sensor pueden variar según el dispositivo y sus condiciones de uso. La aplicación todavía no almacena las lecturas ni los reportes en una base de datos.

### Próxima mejora

Explorar la posibilidad de registrar eventos relacionados con el movimiento en la bitácora, permitiendo al usuario decidir cuándo asociar una lectura con una incidencia.

## Ejecución

El proyecto puede ejecutarse desde Android Studio utilizando un emulador Android compatible o un dispositivo físico con el sensor disponible.

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
