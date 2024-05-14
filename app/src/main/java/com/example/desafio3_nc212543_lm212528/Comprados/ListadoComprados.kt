package com.example.desafio3_nc212543_lm212528.Comprados

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio3_nc212543_lm212528.MainActivity
import com.example.desafio3_nc212543_lm212528.R
import com.example.desafio3_nc212543_lm212528.model.Comprados


class ListadoComprados : AppCompatActivity() {
    private var managerCompras: Comprados? = null

    @SuppressLint("MissingInflatedId", "Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_listado_comprados)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewComprados)
        managerCompras = Comprados(this)
        val intent = intent
        val idLista = intent.getStringExtra("idLista")
        val btnNuevo = findViewById<Button>(R.id.btnAgregarComprados)

        btnNuevo.setOnClickListener {
            val intent = Intent(this, CrearComprado::class.java)
            intent.putExtra("idLista", idLista.toString())
            startActivity(intent)
        }

        val cursor = managerCompras!!.searchCompradosDeLista(idLista.toString())
        if (cursor != null && cursor.moveToFirst()) {
            val compras = mutableListOf<Comprado>()

            var idComprado: Int = 0
            var idLista: Int = 0
            var nombre: String = ""
            var cantidad: String = ""

            while (!cursor.isAfterLast) {
                // Obtener datos del cursor y establecerlos en las vistas
                idComprado = cursor.getInt(cursor.getColumnIndex("id_comprado"))
                idLista = cursor.getInt(cursor.getColumnIndex("id_lista_compra"))
                nombre = cursor.getString(cursor.getColumnIndex("nombre"))
                cantidad = cursor.getString(cursor.getColumnIndex("cantidad"))


                val compra =
                    Comprado(
                        idComprado,
                        idLista,
                        nombre,
                        cantidad
                    )
                compras.add(compra)
                cursor.moveToNext()
            }
            recyclerView.layoutManager = LinearLayoutManager(this@ListadoComprados)
            recyclerView.adapter = AdapterCompras(compras)
            cursor.close()
        } else {
            val txtMensaje = findViewById<TextView>(R.id.txtMensaje)
            txtMensaje.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

    }
}