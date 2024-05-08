package com.example.desafio3_nc212543_lm212528

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.desafio3_nc212543_lm212528.model.Comprados
import com.example.desafio3_nc212543_lm212528.model.Listas

class MainActivity : AppCompatActivity() {
    private var managerListas: Listas? = null
    private var managerComprados: Comprados? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        managerListas = Listas(this)
        managerComprados = Comprados(this)

        val cursorL = managerListas!!.searchFichasAll()
        if (cursorL != null && cursorL.moveToFirst()) {
            /*val tematicas = mutableListOf<Tematica>()
            var idTematica: Int = 0
            var nombreTematica: String = ""
            var descripcionTematica: String = ""
            var colorTematica: String = ""

            while (!cursor.isAfterLast) {
                // Obtener datos del cursor y establecerlos en las vistas
                idTematica = cursor.getInt(cursor.getColumnIndex("id_tematica"))
                nombreTematica = cursor.getString(cursor.getColumnIndex("nombre"))
                descripcionTematica = cursor.getString(cursor.getColumnIndex("descripcion"))
                colorTematica = cursor.getString(cursor.getColumnIndex("color"))

                val tematica =
                    Tematica(
                        idTematica,
                        nombreTematica,
                        descripcionTematica,
                        colorTematica
                    )
                tematicas.add(tematica)
                cursor.moveToNext()
            }
            recyclerView.layoutManager = LinearLayoutManager(this@Dashboard)
            recyclerView.adapter = TematicaAdapter(tematicas, colorTematica)*/
            cursorL.close()
        } else {
            Toast.makeText(
                this, "No se encontro ninguna LISTA.",
                Toast.LENGTH_LONG
            ).show()
        }

        //

        val cursorC = managerComprados!!.searchTematicasAll()
        if (cursorC != null && cursorC.moveToFirst()) {
            /*val tematicas = mutableListOf<Tematica>()
            var idTematica: Int = 0
            var nombreTematica: String = ""
            var descripcionTematica: String = ""
            var colorTematica: String = ""

            while (!cursor.isAfterLast) {
                // Obtener datos del cursor y establecerlos en las vistas
                idTematica = cursor.getInt(cursor.getColumnIndex("id_tematica"))
                nombreTematica = cursor.getString(cursor.getColumnIndex("nombre"))
                descripcionTematica = cursor.getString(cursor.getColumnIndex("descripcion"))
                colorTematica = cursor.getString(cursor.getColumnIndex("color"))

                val tematica =
                    Tematica(
                        idTematica,
                        nombreTematica,
                        descripcionTematica,
                        colorTematica
                    )
                tematicas.add(tematica)
                cursor.moveToNext()
            }
            recyclerView.layoutManager = LinearLayoutManager(this@Dashboard)
            recyclerView.adapter = TematicaAdapter(tematicas, colorTematica)*/
            cursorC.close()
        } else {
            Toast.makeText(
                this, "No se encontro ningun ITEM DE COMPRA.",
                Toast.LENGTH_LONG
            ).show()
        }


    }
}