package com.example.ejerciciosobreut4

import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var Imagen: ImageView
    private lateinit var Ruta: TextView
    private lateinit var Foto: Uri

    private val permisoCamara =
        registerForActivityResult(ActivityResultContracts.RequestPermission()){
            permiso ->
            if (permiso) {
                tomarFoto()
            } else {
                Ruta.text = "Permiso de cámara no concedido"
            }
        }


    private val galeria =
        registerForActivityResult(ActivityResultContracts.GetContent()) {
            uri: Uri? -> uri?.let { Imagen.setImageURI(it)
            Ruta.text = it.toString()
            }
        }

    private val camara =
        registerForActivityResult(ActivityResultContracts.TakePicture()){
            ok ->
            if (ok) {
                Imagen.setImageURI(Foto)
                Ruta.text = Foto.path
            }
        }

    fun tomarFoto(){
        val archivo = crearArchivoImagen()
        Foto = FileProvider.getUriForFile(
            this, "${packageName}.provider",archivo
        )
        camara.launch(Foto)
    }

    fun crearArchivoImagen(): File {
        val fecha = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val guardarFoto = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("IMG_$fecha",".jpg", guardarFoto)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Imagen = findViewById(R.id.Imagen)
        Ruta = findViewById(R.id.Ruta)

        findViewById<Button>(R.id.Galeria).setOnClickListener {
            galeria.launch("image/*")
        }

        findViewById<Button>(R.id.boton).setOnClickListener {
            permisoCamara.launch(android.Manifest.permission.CAMERA)
        }
    }
}