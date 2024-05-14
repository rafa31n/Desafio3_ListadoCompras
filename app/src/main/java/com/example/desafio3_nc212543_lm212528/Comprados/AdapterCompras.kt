package com.example.desafio3_nc212543_lm212528.Comprados

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio3_nc212543_lm212528.R
import com.example.desafio3_nc212543_lm212528.model.Comprados


class AdapterCompras(
    private val comprados: MutableList<Comprado>,

    ) : RecyclerView.Adapter<AdapterCompras.ViewHolder>() {
    private var managerCompras: Comprados? = null

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewNombre: TextView = itemView.findViewById(R.id.textViewNombre)
        val textViewCantidad: TextView = itemView.findViewById(R.id.textViewCantidad)
        val cardView: CardView = itemView.findViewById(R.id.cardViewComprados)
        val btnEditar: Button = itemView.findViewById(R.id.btnEditarCompra)
        val btnEliminar: Button = itemView.findViewById(R.id.btnEliminarCompra)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        managerCompras = Comprados(parent.context)
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.activity_adapter_comprados, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (comprados.isEmpty()) {
            showMessage(holder.itemView.context)
            holder.itemView.visibility = View.GONE
            return
        }

        val comprado = comprados[position]
        holder.textViewNombre.text = comprado.nombre
        holder.textViewCantidad.text = comprado.cantidad

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, ListadoComprados::class.java)
            intent.putExtra("idLista", comprado.idLista)
            holder.cardView.context.startActivity(intent)
        }

        //Botones
        holder.btnEditar.setOnClickListener {
            val intent = Intent(holder.itemView.context, EditarComprado::class.java)
            intent.putExtra("idComprado", comprado.idComprado.toString())
            intent.putExtra("idLista", comprado.idLista.toString())
            intent.putExtra("nombre", comprado.nombre)
            intent.putExtra("cantidad", comprado.cantidad.toString())
            holder.cardView.context.startActivity(intent)
        }

        holder.btnEliminar.setOnClickListener {
            mostrarModalEliminar(holder.itemView.context, comprado)
        }
    }

    override fun getItemCount(): Int {
        return if (comprados.isEmpty()) 1 else comprados.size
    }

    private fun showMessage(context: Context) {
        Toast.makeText(context, "La comprado de Ficha está vacía.", Toast.LENGTH_SHORT).show()

    }

    private fun mostrarModalEliminar(context: Context, compra: Comprado) {
        AlertDialog.Builder(context)
            .setTitle("Eliminar compra")
            .setMessage("¿Estás seguro de que quieres eliminar la compra ${compra.nombre}?")
            .setPositiveButton("Eliminar") { dialog, which ->
                compra.idComprado?.let { it1 -> managerCompras!!.eliminarComprado(it1) }
                comprados.remove(compra)
                notifyDataSetChanged()
                Toast.makeText(
                    context, "Compra ${compra.nombre} eliminada.",
                    Toast.LENGTH_SHORT
                ).show()
            }
            .setNegativeButton("Cancelar") { dialog, which ->
                // No hacer nada, simplemente cerrar el diálogo
                dialog.dismiss()
            }
            .show()
    }
}