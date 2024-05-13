package com.example.desafio3_nc212543_lm212528

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio3_nc212543_lm212528.model.Listas

class AdapterLista(
    private val listas: MutableList<Lista>,

    ):  RecyclerView.Adapter<AdapterLista.ViewHolder>() {
    private var managerListas: Listas? = null
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewANVERSO: TextView = itemView.findViewById(R.id.textViewANVERSO)
        val textViewENVERSO: TextView = itemView.findViewById(R.id.textViewENVERSO)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        managerListas = Listas(parent.context)
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.activity_adapter_lista, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (listas.isEmpty()) {
            showMessage(holder.itemView.context)
            holder.itemView.visibility = View.GONE
            return
        }

        val lista = listas[position]
        holder.textViewANVERSO.text=lista.listaTitulo
        holder.textViewENVERSO.text=lista.listaFecha


        //Botones

    }
    override fun getItemCount(): Int {
        return if (listas.isEmpty()) 1 else listas.size
    }
    private fun showMessage(context: Context) {
        Toast.makeText(context, "La lista de Ficha está vacía.", Toast.LENGTH_SHORT).show()

    }
}