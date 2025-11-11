package com.example.ejerjuntos

import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Actividad2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_actividad2)

        val botonEsp = findViewById<Button>(R.id.BotonEj2)

        botonEsp.setOnClickListener {
            Toast.makeText(this, "Esperando 10 segundos...", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, Actividad2_1::class.java)

            val pendingIntent = PendingIntent.getActivity(
                this, 0, intent,
                PendingIntent.FLAG_IMMUTABLE
            )

            Handler(Looper.getMainLooper()).postDelayed({
                try {
                    pendingIntent.send()
                } catch (e: PendingIntent.CanceledException){
                    e.printStackTrace()
                }
            }, 10000)
        }
    }
}