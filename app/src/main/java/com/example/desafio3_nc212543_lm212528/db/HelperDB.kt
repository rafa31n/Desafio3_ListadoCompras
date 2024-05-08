package com.example.desafio3_nc212543_lm212528.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.desafio3_nc212543_lm212528.model.Comprados
import com.example.desafio3_nc212543_lm212528.model.Listas


class HelperDB(context: Context?) :
    SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    companion object {
        private const val DB_NAME = "listado_compras"
        private const val DB_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(Listas.CREATE_TABLE_LISTAS)
        db.execSQL(Comprados.CREATE_TABLE_COMPRADOS)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }


}