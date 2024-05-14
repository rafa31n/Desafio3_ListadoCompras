package com.example.desafio3_nc212543_lm212528.ListaCompra

import android.annotation.SuppressLint
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
import java.util.Calendar

class NuevaLista : AppCompatActivity() {
    private var managerLista: Listas? = null
    private lateinit var tvSelectedDate: TextView
    private lateinit var btnShowDatePicker: Button
    private lateinit var fechaSeleccionada: String

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_nueva_lista)
        managerLista = Listas(this)
        val txtDescripcion = findViewById<EditText>(R.id.editTextDescripcion)
        val btnGuardar = findViewById<Button>(R.id.btnGuardarTematica)
        val buttonCancelar = findViewById<Button>(R.id.buttonCancelar)

        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        btnShowDatePicker = findViewById(R.id.btnShowDatePicker)

        btnShowDatePicker.setOnClickListener {
            showDatePickerDialog()
        }

        btnGuardar.setOnClickListener {
            var descricion: String = ""
            descricion = txtDescripcion.text.toString()

            if (fechaSeleccionada.isNotEmpty() && descricion.isNotEmpty()) {
                managerLista!!.addNewTematica(fechaSeleccionada, descricion)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                Toast.makeText(
                    this, "Tematica se ha guardados correctamente.",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                Toast.makeText(
                    this, "Completa todos los campo.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        buttonCancelar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }


    private fun showDatePickerDialog() {
        // Obtén la fecha actual
        val calendar = Calendar.getInstance()
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
                tvSelectedDate.text = txtFecha + ": " + selectedDate
                fechaSeleccionada = selectedDate
            },
            year,
            month,
            day
        )
        datePickerDialog.datePicker.minDate = calendar.timeInMillis
        datePickerDialog.show()
    }
}