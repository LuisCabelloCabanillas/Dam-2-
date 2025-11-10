package com.example.ejer3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val boton1 = findViewById<Button>(R.id.button1)
        val boton2 = findViewById<Button>(R.id.button2)
        val boton3 = findViewById<Button>(R.id.button3)
        val boton4 = findViewById<Button>(R.id.button4)

        boton1.setOnClickListener {
            val intent = Intent(this, Actividad1::class.java)
            startActivity(intent)
        }

        boton2.setOnClickListener {
            val intent = Intent(this, Actividad2::class.java)
            startActivity(intent)
        }

        boton3.setOnClickListener {
            val intent = Intent(this, Actividad3::class.java)
            startActivity(intent)
        }

        boton4.setOnClickListener {
            val intent = Intent(this, Actividad4::class.java)
            startActivity(intent)
        }

    }
}