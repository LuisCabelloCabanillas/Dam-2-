package com.example.ejerjuntos

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Actividad5 : AppCompatActivity() {

    private lateinit var pantalla: TextView
    private var numeroActual = ""
    private var operador = ""
    private var primerNumero = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_actividad5)

        pantalla = findViewById(R.id.Pantalla)

        val botonesNumeros = listOf(
            R.id.boton0, R.id.boton1, R.id.boton2, R.id.boton3,
            R.id.boton4, R.id.boton5, R.id.boton6, R.id.boton7, R.id.boton8, R.id.boton9
        )

        for (id in botonesNumeros){
            findViewById<Button>(id).setOnClickListener { boton ->
                numeroActual += (boton as Button).text
                pantalla.text = numeroActual
            }
        }

        findViewById<Button>(R.id.botonPun).setOnClickListener {
            if (!numeroActual.contains(".")) {
                numeroActual += "."
                pantalla.text = numeroActual
            }
        }

        findViewById<Button>(R.id.botonSum).setOnClickListener { operador("+") }
        findViewById<Button>(R.id.botonRes).setOnClickListener { operador("-") }
        findViewById<Button>(R.id.botonMul).setOnClickListener { operador("*") }
        findViewById<Button>(R.id.botonDiv).setOnClickListener { operador("/") }

        findViewById<Button>(R.id.botonIgu).setOnClickListener {
            if (numeroActual.isNotEmpty()) {
                val segundoNumero = numeroActual.toDouble()
                val resultado = when (operador) {
                    "+" -> primerNumero + segundoNumero
                    "-" -> primerNumero - segundoNumero
                    "*" -> primerNumero * segundoNumero
                    "/" -> if (segundoNumero!=0.0) primerNumero / segundoNumero else Double.NaN
                    else -> 0.0
                }
                pantalla.text = resultado.toString()
                numeroActual = ""
                operador = ""
            }
        }

        findViewById<Button>(R.id.botonLim).setOnClickListener {
            numeroActual= ""
            operador = ""
            primerNumero= 0.0
            pantalla.text= "0"
        }

    }
    private fun operador(op: String) {
        if (numeroActual.isNotEmpty()) {
            primerNumero = numeroActual.toDouble()
            operador = op
            numeroActual= ""
        }
    }
}