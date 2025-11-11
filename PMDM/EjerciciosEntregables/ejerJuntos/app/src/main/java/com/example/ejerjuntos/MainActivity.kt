package com.example.ejerjuntos

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.Botón1).setOnClickListener {
            val intent = Intent(this, Actividad1::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.Botón2).setOnClickListener {
            val intent = Intent(this, Actividad2::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.Botón3).setOnClickListener {
            val intent = Intent(this, Actividad3_2::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.Botón4).setOnClickListener {
            val intent = Intent(this, Actividad4::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.Botón5).setOnClickListener {
            val intent = Intent(this, Actividad5::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.Botón6).setOnClickListener {
            val intent = Intent(this, Actividad6::class.java)
            startActivity(intent)
        }
    }
}