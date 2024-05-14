package com.example.desafio3_nc212543_lm212528.Comprados

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.desafio3_nc212543_lm212528.R
import com.example.desafio3_nc212543_lm212528.model.Comprados

class CrearComprado : AppCompatActivity() {
    private var managerCompras: Comprados? = null

    @SuppressLint("WrongViewCast", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_crear_comprado)

        val intent = intent
        val idLista = intent.getStringExtra("idLista")
        managerCompras = Comprados(this)

        val textViewNombre = findViewById<EditText>(R.id.editTextNombre)
        val textViewCantidad = findViewById<EditText>(R.id.editTextCantidad)

        val btnGuardar = findViewById<Button>(R.id.btnGuardarItem)
        val btnCancelar = findViewById<Button>(R.id.buttonCancelar)

        btnGuardar.setOnClickListener {
            if (textViewCantidad.text != null) {
                val cantidadText = textViewCantidad.text.toString()
                managerCompras!!.addNewComprado(
                    idLista?.toInt(),
                    textViewNombre.text.toString(),
                    cantidadText.toInt()
                )
                Toast.makeText(
                    this, "Item guardado correctamente",
                    Toast.LENGTH_LONG
                ).show()
                val intent = Intent(this, ListadoComprados::class.java)
                intent.putExtra("idLista", idLista)
                startActivity(intent)
            }
        }


        btnCancelar.setOnClickListener {
            val intent = Intent(this, ListadoComprados::class.java)
            intent.putExtra("idLista", idLista)
            startActivity(intent)
        }
    }

    fun funValidarText(text: String): Boolean {
        return !text.isEmpty()
    }
}