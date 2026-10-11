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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import sv.edu.utec.etps1.registroincidencias.ui.theme.RegistroIncidenciasTheme
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

    // Preparación del acelerómetro
    val context = LocalContext.current

    val sensorManager = remember(context) {
        context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    val acelerometro = remember(sensorManager) {
        sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    }

    var ejeX by remember { mutableStateOf(0f) }
    var ejeY by remember { mutableStateOf(0f) }
    var ejeZ by remember { mutableStateOf(0f) }

    var estadoMovimiento by remember {
        mutableStateOf("Esperando lectura del sensor")
    }

    // Escuchar el sensor y liberar el recurso al salir de la pantalla
    DisposableEffect(sensorManager, acelerometro) {
        if (acelerometro != null) {
            val listener = object : SensorEventListener {
                override fun onSensorChanged(event: SensorEvent) {
                    val x = event.values[0]
                    val y = event.values[1]
                    val z = event.values[2]

                    ejeX = x
                    ejeY = y
                    ejeZ = z

                    // La gravedad normalmente aporta unos 9.8 m/s².
                    // Una diferencia amplia puede indicar movimiento.
                    val magnitud = sqrt(
                        x * x + y * y + z * z
                    )

                    estadoMovimiento = if (
                        kotlin.math.abs(magnitud - 9.8f) > 2.0f
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
                    // No necesitamos cambiar la interfaz aquí.
                }
            }

            sensorManager.registerListener(
                listener,
                acelerometro,
                SensorManager.SENSOR_DELAY_NORMAL
            )

            onDispose {
                sensorManager.unregisterListener(listener)
            }
        } else {
            estadoMovimiento = "Acelerómetro no disponible"

            onDispose { }
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

        // Nueva sección del acelerómetro
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Sensor de movimiento",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(12.dp))

                if (acelerometro != null) {
                    Text("Estado del sensor: Disponible")

                    Spacer(modifier = Modifier.height(8.dp))

                    Text("Eje X: %.2f m/s²".format(ejeX))
                    Text("Eje Y: %.2f m/s²".format(ejeY))
                    Text("Eje Z: %.2f m/s²".format(ejeZ))

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Interpretación: $estadoMovimiento",
                        style = MaterialTheme.typography.bodyLarge
                    )
                } else {
                    Text(
                        text = "Este dispositivo no tiene un acelerómetro disponible."
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegistroIncidenciasPreview() {
    RegistroIncidenciasTheme {
        RegistroIncidenciasApp()
    }
}