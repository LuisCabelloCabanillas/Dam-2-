package com.example.ejerjuntos

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Actividad1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_actividad1)

        val EditTextoUrl= findViewById<EditText>(R.id.EditText1)
        val AbrirBot= findViewById<Button>(R.id.Botón)

        AbrirBot.setOnClickListener {
            val textoUrl = EditTextoUrl.text.toString().trim()

            if (textoUrl.isEmpty()){
                Toast.makeText(this, "Por favor, introduce una URL", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!Patterns.WEB_URL.matcher(textoUrl).matches()){
                Toast.makeText(this, "Introduce una URL válida", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val uri = if (!textoUrl.startsWith("http://") && !textoUrl.startsWith("https://")) {
                Uri.parse("http://$textoUrl")
            } else {
                Uri.parse(textoUrl)
            }

            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
    }
}