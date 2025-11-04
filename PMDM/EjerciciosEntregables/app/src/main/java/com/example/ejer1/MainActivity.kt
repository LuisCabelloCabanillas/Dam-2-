package com.example.ejer1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val EditTextoUrl= findViewById<EditText>(R.id.EditText1)
        val AbrirBot= findViewById<Button>(R.id.Botón)

        AbrirBot.setOnClickListener {
            val textoUrl = EditTextoUrl.text.toString().trim()

            if (textoUrl.isEmpty()){
                Toast.makeText(this, "Por favor, introduce una URL", Toast.LENGTH_SHORT)
                return@setOnClickListener
            }

            if ()
        }
    }
}