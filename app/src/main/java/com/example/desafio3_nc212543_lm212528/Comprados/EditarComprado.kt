package com.example.desafio3_nc212543_lm212528.Comprados

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.desafio3_nc212543_lm212528.R
import com.example.desafio3_nc212543_lm212528.model.Comprados

class EditarComprado : AppCompatActivity() {
    private var managerCompras: Comprados? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_editar_comprado)

        managerCompras = Comprados(this)

        val editTextNombre = findViewById<EditText>(R.id.editTextNombre)
        val editTextCantidad = findViewById<EditText>(R.id.editTextCantidad)

        val btnEditar = findViewById<Button>(R.id.btnEditarItem)
        val btnCancelar = findViewById<Button>(R.id.buttonCancelar)

        // Obtener parametros intent
        val intent = intent
        val idComprado = intent.getStringExtra("idComprado")
        val idLista = intent.getStringExtra("idLista")

        val nombre = intent.getStringExtra("nombre")
        val cantidad = intent.getStringExtra("cantidad")

        editTextNombre.setText(nombre)
        editTextCantidad.setText(cantidad)

        btnEditar.setOnClickListener {
            if (idComprado != null) {
                if (idLista != null) {
                    val cantidadText = editTextCantidad.text.toString()
                    managerCompras!!.modificarComprado(
                        idComprado.toInt(),
                        idLista.toInt(),
                        editTextNombre.text.toString(),
                        cantidadText.toInt()
                    )
                }
                Toast.makeText(
                    this, "Cambios guardados correctamente.",
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
}