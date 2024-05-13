package com.example.desafio3_nc212543_lm212528

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.desafio3_nc212543_lm212528.model.Listas
import java.text.SimpleDateFormat
import java.util.Date

class NuevaLista : AppCompatActivity() {
    private var managerLista: Listas? = null
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_nueva_lista)
        managerLista = Listas(this)
        val txtDescripcion = findViewById<EditText>(R.id.editTextDescripcion)
        val btnGuardar = findViewById<Button>(R.id.btnGuardarTematica)

        btnGuardar.setOnClickListener{
            var tematica: String = ""
            var descricion: String = ""
            descricion=txtDescripcion.text.toString()
            val sdf = SimpleDateFormat("dd-M-yyyy")
            val currentDate = sdf.format(Date())
            println("fecha actia $currentDate , Des $descricion")
            managerLista!!.addNewTematica(currentDate,descricion)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            Toast.makeText(
                this, "Tematica se ha guardados correctamente.",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}