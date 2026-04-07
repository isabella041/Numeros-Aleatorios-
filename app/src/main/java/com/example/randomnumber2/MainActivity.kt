package com.example.randomnumber2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.randomnumber2.ui.theme.RandomNumber2Theme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RandomNumber2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NumeroAleatorioUI()
                }
            }
        }
    }
}

@Composable
fun NumeroAleatorioUI() {

    // 🔹 Estados
    var numero by remember { mutableStateOf<Int?>(null) }
    var numeroA by remember { mutableStateOf<Int?>(null) }
    var numeroB by remember { mutableStateOf<Int?>(null) }
    var mayor by remember { mutableStateOf("") }
    var baloto by remember { mutableStateOf<List<Int>>(emptyList()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // 🔹 Título
        Text(
            text = " Generador Aleatorio",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp
            )
        )

        // 🔹 Número principal
        Card(
            modifier = Modifier.fillMaxWidth(0.6f),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD)),
            elevation = CardDefaults.cardElevation(6.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = numero?.toString() ?: "Número Aleatorio",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF0D47A1)
                    )
                )
            }
        }

        // 🔹 Botones principales
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {

            Button(onClick = {
                numero = Random.nextInt(0, 100)
            }) {
                Text("Generar")
            }

            Button(onClick = {
                numero?.let {
                    if (it < 100) numero = it + 1
                }
            }) {
                Text("+1")
            }

            Button(onClick = {
                numero?.let {
                    if (it > 1) numero = it / 2
                }
            }) {
                Text("÷2")
            }
        }

        // 🔹 Generar A y B
        Button(onClick = {
            val a = Random.nextInt(0, 100)
            val b = Random.nextInt(0, 100)

            numeroA = a
            numeroB = b

            mayor = when {
                a > b -> "El mayor es A: $a"
                b > a -> "El mayor es B: $b"
                else -> "Son iguales ($a)"
            }
        }) {
            Text("Generar A y B")
        }

        // 🔹 Generar Baloto
        Button(onClick = {
            baloto = (1..45).shuffled().take(6).sorted()
        }) {
            Text("Generar Baloto")
        }

        // 🔹 Resultados
        Spacer(modifier = Modifier.height(20.dp))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            // A y B
            if (numeroA != null && numeroB != null) {
                Text("A = $numeroA, B = $numeroB")
                Text(mayor)
            }

            // Baloto
            if (baloto.isNotEmpty()) {

                Text(
                    text = "🎱 Números ganadores",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold
                    )
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    baloto.forEach {
                        NumeroBolita(it)
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun NumeroBolita(numero: Int) {

    val color = Color(
        Random.nextInt(256),
        Random.nextInt(256),
        Random.nextInt(256)
    )

    Box(
        modifier = Modifier
            .size(55.dp)
            .clip(CircleShape)
            .background(color)
            .border(2.dp, Color.Black, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = numero.toString(),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewApp() {
    RandomNumber2Theme {
        NumeroAleatorioUI()
    }
}




