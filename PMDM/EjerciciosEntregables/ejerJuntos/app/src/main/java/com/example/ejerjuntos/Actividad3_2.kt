package com.example.ejerjuntos

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Actividad3_2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actividad32)

        val boton1 = findViewById<Button>(R.id.button1)
        val boton2 = findViewById<Button>(R.id.button2)
        val boton3 = findViewById<Button>(R.id.button3)

        boton1.setOnClickListener {
            val intent = Intent(this, Actividad3_1::class.java)
            startActivity(intent)
        }

        boton2.setOnClickListener {
            val intent = Intent(this, Actividad3_3::class.java)
            startActivity(intent)
        }

        boton3.setOnClickListener {
            val intent = Intent(this, Actividad3_4::class.java)
            startActivity(intent)
        }
    }
}