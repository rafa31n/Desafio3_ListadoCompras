package com.example.desafio3_nc212543_lm212528.model

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.desafio3_nc212543_lm212528.db.HelperDB

class Comprados(context: Context?) {
    private var helper: HelperDB? = null
    private var db: SQLiteDatabase? = null

    init {
        helper = HelperDB(context)
        db = helper!!.getWritableDatabase()
    }

    companion object {
        //TABLA TEMATICAS
        val TABLE_NAME_COMPRADOS = "comprados"

        //nombre de los campos de la tabla tematicas
        val COL_ID = "id_comprado"
        val COL_IDLISTACOMPRA = "id_lista_compra"
        val COL_NOMBRE = "nombre"
        val COL_CANTIDAD = "cantidad"


        //sentencia SQL para crear la tabla.
        val CREATE_TABLE_COMPRADOS = (
                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_COMPRADOS + "("
                        + COL_ID + " integer primary key autoincrement,"
                        + COL_IDLISTACOMPRA + " integer NOT NULL,"
                        + COL_NOMBRE + " varchar(255) NOT NULL,"
                        + COL_CANTIDAD + " integer NOT NULL);"
                )
    }

    // ContentValues
    fun generarContentValues(
        id_lista_compra: Int?,
        nombre: String?,
        cantidad: Int?,
    ): ContentValues? {
        val valores = ContentValues()
        valores.put(Comprados.COL_IDLISTACOMPRA, id_lista_compra)
        valores.put(Comprados.COL_NOMBRE, nombre)
        valores.put(Comprados.COL_CANTIDAD, cantidad)
        return valores
    }

    //Agregar un nuevo registro
    fun addNewComprado(
        idLista: Int?,
        nombre: String?,
        cantidad: Int?
    ) {
        db!!.insert(
            TABLE_NAME_COMPRADOS,
            null,
            generarContentValues(idLista, nombre, cantidad)
        )
    }

    // Eliminar un registro
    fun eliminarComprado(id: Int) {
        db!!.delete(TABLE_NAME_COMPRADOS, "$COL_ID=?", arrayOf(id.toString()))
    }

    //Modificar un registro
    fun modificarComprado(
        id: Int,
        id_listado: Int?,
        nombre: String?,
        cantidad: Int?
    ) {
        db!!.update(
            TABLE_NAME_COMPRADOS, generarContentValues(
                id_listado, nombre, cantidad
            ),
            "$COL_ID=?", arrayOf(id.toString())
        )
    }

    // Mostrar un registro particular
    fun searchCompradosDeLista(id: String?): Cursor? {
        val columns = arrayOf(
            COL_ID, COL_IDLISTACOMPRA, COL_NOMBRE, COL_CANTIDAD
        )
        return db!!.query(
            TABLE_NAME_COMPRADOS, columns,
            "$COL_IDLISTACOMPRA=?", arrayOf(id.toString()), null, null, null
        )
    }

    // Mostrar todos los registros
    fun searchTematicasAll(): Cursor? {
        val columns = arrayOf(
            COL_ID, COL_IDLISTACOMPRA, COL_NOMBRE, COL_CANTIDAD
        )
        return db!!.query(
            TABLE_NAME_COMPRADOS, columns,
            null, null, null, null,
            "${Comprados.COL_NOMBRE} ASC"
        )
    }
}