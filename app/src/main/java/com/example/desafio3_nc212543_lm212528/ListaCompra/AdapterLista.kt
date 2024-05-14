package com.example.desafio3_nc212543_lm212528.ListaCompra

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio3_nc212543_lm212528.Comprados.ListadoComprados
import com.example.desafio3_nc212543_lm212528.R
import com.example.desafio3_nc212543_lm212528.model.Comprados
import com.example.desafio3_nc212543_lm212528.model.Listas

class AdapterLista(
    private val listas: MutableList<Lista>,

    ) : RecyclerView.Adapter<AdapterLista.ViewHolder>() {
    private var managerListas: Listas? = null
    private var managerCompras: Comprados? = null

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewTitulo: TextView = itemView.findViewById(R.id.textViewTitulo)
        val textViewFecha: TextView = itemView.findViewById(R.id.textViewFecha)
        val cardView: CardView = itemView.findViewById(R.id.cardViewFichas)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        managerListas = Listas(parent.context)
        managerCompras = Comprados(parent.context)
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.activity_adapter_lista, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (listas.isEmpty()) {
            showMessage(holder.itemView.context)
            holder.itemView.visibility = View.GONE
            return
        }

        val lista = listas[position]
        holder.textViewTitulo.text = lista.listaTitulo
        holder.textViewFecha.text = lista.listaFecha

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, ListadoComprados::class.java)
            intent.putExtra("idLista", lista.idLista.toString())
            holder.cardView.context.startActivity(intent)
        }

        holder.itemView.setOnLongClickListener {
            modalOpciones(holder.itemView.context, lista)
            true
        }
    }

    override fun getItemCount(): Int {
        return if (listas.isEmpty()) 1 else listas.size
    }

    private fun showMessage(context: Context) {
        Toast.makeText(context, "La lista de Ficha está vacía.", Toast.LENGTH_SHORT).show()

    }

    private fun modalOpciones(context: Context?, lista: Lista) {
        AlertDialog.Builder(context)
            .setTitle("Opciones para ${lista.listaTitulo}")
            .setMessage("¿Qué deseas hacer con esta lista?")
            .setPositiveButton("Eliminar") { dialog, which ->
                lista.idLista?.let { it1 -> managerListas!!.eliminarLista(it1) }
                listas.remove(lista)
                notifyDataSetChanged()
                Toast.makeText(
                    context, "Lista ${lista.listaTitulo} eliminada.",
                    Toast.LENGTH_SHORT
                ).show()
                // Eliminar todas las fichas de la tematica
                lista.idLista?.let { managerCompras?.eliminarCompradosLista(it) }
            }
            .setNegativeButton("Editar") { dialog, which ->
                val intent = Intent(context, EditarListaCompra::class.java)
                intent.putExtra("idLista", lista.idLista.toString())
                intent.putExtra("listaTitulo", lista.listaTitulo)
                intent.putExtra("listaFecha", lista.listaFecha)
                context?.startActivity(intent)
            }
            .setNeutralButton("Cancelar") { dialog, which ->
            }
            .show()
    }
}