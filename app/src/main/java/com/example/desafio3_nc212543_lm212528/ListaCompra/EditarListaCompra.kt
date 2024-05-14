package com.example.desafio3_nc212543_lm212528.ListaCompra

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.desafio3_nc212543_lm212528.MainActivity
import com.example.desafio3_nc212543_lm212528.R
import com.example.desafio3_nc212543_lm212528.model.Listas
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class EditarListaCompra : AppCompatActivity() {
    private var managerLista: Listas? = null
    private lateinit var tvSelectedDate: TextView
    private lateinit var btnShowDatePicker: Button
    private lateinit var fechaSeleccionada: String
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
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        btnShowDatePicker = findViewById(R.id.btnShowDatePicker)

        btnShowDatePicker.setOnClickListener {
            if (listaFecha != null) {
                showDatePickerDialog(listaFecha)
            }
        }

        val btnEditarLista = findViewById<Button>(R.id.btnEditarLista)
        val buttonCancelar = findViewById<Button>(R.id.buttonCancelar)

        txtTitulo.setText(listaTitulo)
        tvSelectedDate.text = listaFecha

        btnEditarLista.setOnClickListener {
            if (txtTitulo.text.isNotEmpty()) {
                if (idLista != null) {
                    managerLista!!.modificarLista(
                        idLista.toInt(),
                        fechaSeleccionada,
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

    private fun showDatePickerDialog(dateString: String) {
        // Parsea la cadena de fecha en valores numéricos
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val date = sdf.parse(dateString)
        val calendar = Calendar.getInstance()
        date?.let { calendar.time = it }

        // Obtiene los valores de año, mes y día del calendario
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        // Crea y muestra el DatePickerDialog
        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                // Actualiza el TextView con la fecha seleccionada
                val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                val txtFecha = tvSelectedDate.text.toString()
                tvSelectedDate.text = "$txtFecha: $selectedDate"
                fechaSeleccionada = selectedDate
            },
            year,
            month,
            day
        )

        // Establece la fecha mínima del DatePickerDialog como el día actual
        datePickerDialog.datePicker.minDate = System.currentTimeMillis()
        datePickerDialog.show()
    }
}