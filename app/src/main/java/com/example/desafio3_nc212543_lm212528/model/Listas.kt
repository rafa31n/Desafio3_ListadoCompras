package com.example.desafio3_nc212543_lm212528.model

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.desafio3_nc212543_lm212528.db.HelperDB

class Listas(context: Context?) {
    private var helper: HelperDB? = null
    private var db: SQLiteDatabase? = null

    init {
        helper = HelperDB(context)
        db = helper!!.getWritableDatabase()
    }

    companion object {
        //TABLA TEMATICAS
        val TABLE_NAME_LISTAS = "listas"

        //nombre de los campos de la tabla listas
        val COL_ID = "id_lista"
        val COL_FECHA = "fecha_lista"
        val COL_TITULO = "titulo_lista"

        //sentencia SQL para crear la tabla.
        val CREATE_TABLE_LISTAS = (
                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_LISTAS + "("
                        + COL_ID + " integer primary key autoincrement,"
                        + COL_FECHA + " varchar(255) NOT NULL,"
                        + COL_TITULO + " varchar(255) NOT NULL );"
                )
    }

    // ContentValues
    fun generarContentValues(
        fecha_lista: String?,
        titulo_lista: String?
    ): ContentValues? {
        val valores = ContentValues()
        valores.put(Listas.COL_FECHA, fecha_lista)
        valores.put(Listas.COL_TITULO, titulo_lista)
        return valores
    }
    fun addNewTematica(
        fecha_lista: String?,
        titulo_lista: String?
    ) {
        db!!.insert(
            TABLE_NAME_LISTAS,
            null,
            generarContentValues(fecha_lista, titulo_lista)
        )
    }

    // Eliminar un registro
    fun eliminarLista(id: Int) {
        db!!.delete(TABLE_NAME_LISTAS, "$COL_ID=?", arrayOf(id.toString()))
    }

    //Modificar un registro
    fun modificarLista(
        id_lista: Int,
        fecha_lista: String?,
        titulo_lista: String?
    ) {
        db!!.update(
            TABLE_NAME_LISTAS, generarContentValues(
                fecha_lista, titulo_lista
            ),
            "$COL_ID=?", arrayOf(id_lista.toString())
        )
    }

    // Mostrar un registro particular
    /*fun searchProducto(id: Int): Cursor? {
        val columns = arrayOf(
            COL_ID, COL_IDCATEGORIA, COL_DESCRIPCION, COL_PRECIO,
            COL_CANTIDAD
        )
        return db!!.query(
            TABLE_NAME_PRODUCTOS, columns,
            "$COL_ID=?", arrayOf(id.toString()), null, null, null
        )
    }*/

    // Mostrar todos los registros
    fun searchFichasAll(): Cursor? {
        val columns = arrayOf(
            COL_ID, COL_FECHA, COL_TITULO
        )
        return db!!.query(
            TABLE_NAME_LISTAS, columns,
            null, null, null, null, "${Listas.COL_FECHA} ASC"
        )
    }
}