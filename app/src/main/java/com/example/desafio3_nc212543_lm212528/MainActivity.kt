package com.example.desafio3_nc212543_lm212528

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio3_nc212543_lm212528.ListaCompra.AdapterLista
import com.example.desafio3_nc212543_lm212528.ListaCompra.Lista
import com.example.desafio3_nc212543_lm212528.ListaCompra.NuevaLista
import com.example.desafio3_nc212543_lm212528.model.Comprados
import com.example.desafio3_nc212543_lm212528.model.Listas


class MainActivity : AppCompatActivity() {
    private var managerListas: Listas? = null
    private var managerComprados: Comprados? = null

    @SuppressLint("Range", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewlista)
        val btnNuevaLista = findViewById<Button>(R.id.fabAgregarLista)
        managerListas = Listas(this)

        btnNuevaLista.setOnClickListener {
            val intent = Intent(this, NuevaLista::class.java)
            startActivity(intent)
        }

        //managerListas!!.addNewTematica("2052", "prueba121")
        val cursor = managerListas!!.searchFichasAll()
        if (cursor != null && cursor.moveToFirst()) {
            val listas = mutableListOf<Lista>()
            var id_lista: Int = 0

            var fecha_lista: String = ""
            var titulo_lista: String = ""

            while (!cursor.isAfterLast) {
                // Obtener datos del cursor y establecerlos en las vistas
                id_lista = cursor.getInt(cursor.getColumnIndex("id_lista"))
                fecha_lista = cursor.getString(cursor.getColumnIndex("fecha_lista"))

                titulo_lista = cursor.getString(cursor.getColumnIndex("titulo_lista"))

                val lista =
                    Lista(
                        id_lista,
                        fecha_lista,
                        titulo_lista
                    )
                listas.add(lista)
                cursor.moveToNext()
            }
            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            recyclerView.adapter = AdapterLista(listas)
            cursor.close()
        } else {
            Toast.makeText(
                this, "No se encontro ninguna ficha.",
                Toast.LENGTH_LONG
            ).show()
        }
    }

}