
package sv.edu.utec.etps1.registroincidencias

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import sv.edu.utec.etps1.registroincidencias.ui.theme.RegistroIncidenciasTheme
import java.util.Locale
import kotlin.math.abs
import kotlin.math.sqrt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RegistroIncidenciasTheme {
                RegistroIncidenciasApp()
            }
        }
    }
}

@Composable
fun RegistroIncidenciasApp() {
    var titulo by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var prioridad by remember { mutableStateOf("") }
    var mensaje by remember {
        mutableStateOf("Aún no hay reporte creado")
    }

    val context = LocalContext.current

    val sensorManager = remember(context) {
        context.getSystemService(Context.SENSOR_SERVICE) as? SensorManager
    }

    val acelerometro = remember(sensorManager) {
        sensorManager?.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    }

    var ejeX by remember { mutableStateOf(0f) }
    var ejeY by remember { mutableStateOf(0f) }
    var ejeZ by remember { mutableStateOf(0f) }

    var estadoMovimiento by remember {
        mutableStateOf("Esperando lectura del sensor")
    }

    var lecturaRecibida by remember {
        mutableStateOf(false)
    }

    // Registrar y liberar el listener según el ciclo de vida
    // de este componente de Compose.
    DisposableEffect(sensorManager, acelerometro) {
        if (sensorManager == null || acelerometro == null) {
            estadoMovimiento = "Acelerómetro no disponible"

            onDispose { }
        } else {
            estadoMovimiento = "Esperando lectura del sensor"
            lecturaRecibida = false

            val listener = object : SensorEventListener {
                override fun onSensorChanged(event: SensorEvent?) {
                    if (
                        event == null ||
                        event.sensor.type != Sensor.TYPE_ACCELEROMETER
                    ) {
                        return
                    }

                    ejeX = event.values[0]
                    ejeY = event.values[1]
                    ejeZ = event.values[2]
                    lecturaRecibida = true

                    // La magnitud incluye la gravedad.
                    // Esta regla ofrece una interpretación aproximada.
                    val magnitud = sqrt(
                        ejeX * ejeX +
                                ejeY * ejeY +
                                ejeZ * ejeZ
                    )

                    estadoMovimiento = if (
                        abs(magnitud - SensorManager.GRAVITY_EARTH) > 2.0f
                    ) {
                        "Movimiento detectado"
                    } else {
                        "Dispositivo estable"
                    }
                }

                override fun onAccuracyChanged(
                    sensor: Sensor?,
                    accuracy: Int
                ) {
                    // No se requiere una acción adicional por ahora.
                }
            }

            val registrado = sensorManager.registerListener(
                listener,
                acelerometro,
                SensorManager.SENSOR_DELAY_NORMAL
            )

            if (!registrado) {
                estadoMovimiento =
                    "No fue posible iniciar la lectura del sensor"
            }

            onDispose {
                sensorManager.unregisterListener(listener)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Registro de bitácora",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Aplicación para registrar y consultar actividades, eventos e incidencias de un sistema de manera organizada."
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Formulario de registro de incidencias
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Registrar incidencia",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = titulo,
                    onValueChange = { titulo = it },
                    label = { Text("Título") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Sentences,
                        imeAction = ImeAction.Next
                    )
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = descripcion,
                    onValueChange = { descripcion = it },
                    label = { Text("Descripción") },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 3,
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Sentences,
                        imeAction = ImeAction.Done
                    )
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Selecciona la prioridad:",
                    style = MaterialTheme.typography.titleSmall
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .padding(4.dp)
                            .clickable {
                                prioridad = "Baja"
                                mensaje = "Prioridad seleccionada: Baja"
                            }
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text("Baja")
                        }
                    }

                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .padding(4.dp)
                            .clickable {
                                prioridad = "Media"
                                mensaje = "Prioridad seleccionada: Media"
                            }
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text("Media")
                        }
                    }

                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .padding(4.dp)
                            .clickable {
                                prioridad = "Alta"
                                mensaje = "Prioridad seleccionada: Alta"
                            }
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text("Alta")
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {
                        mensaje = when {
                            titulo.isBlank() ->
                                "Ingrese un título para crear el reporte"

                            prioridad.isBlank() ->
                                "Seleccione una prioridad para crear el reporte"

                            else ->
                                "Reporte preparado: $titulo - Prioridad: $prioridad"
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF4CAF50),
                        contentColor = Color.White
                    )
                ) {
                    Text("Crear reporte")
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = mensaje,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Diagnóstico del dispositivo
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Diagnóstico del dispositivo",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = when {
                        sensorManager == null ->
                            "Estado del sensor: Servicio no disponible"

                        acelerometro == null ->
                            "Estado del sensor: Acelerómetro no disponible"

                        estadoMovimiento.startsWith("No fue posible") ->
                            "Estado del sensor: Error al iniciar la lectura"

                        else ->
                            "Estado del sensor: Disponible"
                    }
                )

                Spacer(modifier = Modifier.height(8.dp))

                if (sensorManager != null && acelerometro != null) {
                    Text(
                        text = "Sensor: ${acelerometro.name}"
                    )

                    Text(
                        text = "Eje X: ${formatearValor(ejeX)} m/s²"
                    )

                    Text(
                        text = "Eje Y: ${formatearValor(ejeY)} m/s²"
                    )

                    Text(
                        text = "Eje Z: ${formatearValor(ejeZ)} m/s²"
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = if (lecturaRecibida) {
                            "Interpretación: $estadoMovimiento"
                        } else {
                            "Interpretación: esperando la primera lectura"
                        },
                        style = MaterialTheme.typography.bodyLarge
                    )
                } else {
                    Text(
                        text = "No se pueden obtener lecturas porque el acelerómetro no está disponible."
                    )
                }
            }
        }
    }
}

private fun formatearValor(valor: Float): String {
    return String.format(Locale.getDefault(), "%.2f", valor)
}

@Preview(showBackground = true)
@Composable
fun RegistroIncidenciasPreview() {
    RegistroIncidenciasTheme {
        RegistroIncidenciasApp()
    }
}
