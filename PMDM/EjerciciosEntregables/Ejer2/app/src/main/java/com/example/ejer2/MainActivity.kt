package com.example.ejer2

import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val botonEsp = findViewById<Button>(R.id.Boton)

        botonEsp.setOnClickListener {
            Toast.makeText(this, "Esperando 10 segundos...", Toast.LENGTH_SHORT).show()

            val intent = Intent(this,ActividadDos::class.java)

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