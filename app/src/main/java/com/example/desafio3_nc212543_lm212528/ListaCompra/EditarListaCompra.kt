package com.example.desafio3_nc212543_lm212528.ListaCompra

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.desafio3_nc212543_lm212528.MainActivity
import com.example.desafio3_nc212543_lm212528.R
import com.example.desafio3_nc212543_lm212528.model.Listas

class EditarListaCompra : AppCompatActivity() {
    private var managerLista: Listas? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_editar_lista_compra)

        managerLista = Listas(this)
        val intent = intent
        val idLista = intent.getStringExtra("idLista")
        val listaTitulo = intent.getStringExtra("listaTitulo")
        val listaFecha = intent.getStringExtra("listaFecha")

        val txtTitulo = findViewById<EditText>(R.id.editTextTituloE)
        val btnEditarLista = findViewById<Button>(R.id.btnEditarLista)
        val buttonCancelar = findViewById<Button>(R.id.buttonCancelar)

        txtTitulo.setText(listaTitulo)

        btnEditarLista.setOnClickListener {
            if (txtTitulo.text.isNotEmpty()) {
                if (idLista != null) {
                    managerLista!!.modificarLista(
                        idLista.toInt(),
                        listaFecha,
                        txtTitulo.text.toString()
                    )
                    Toast.makeText(
                        this,
                        "Cambios guardados con exito.",
                        Toast.LENGTH_LONG
                    ).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }

            } else {
                Toast.makeText(
                    this, "Ingrese todos los valores",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        buttonCancelar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}