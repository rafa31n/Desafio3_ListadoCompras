package com.example.desafio3_nc212543_lm212528.Comprados

class Comprado {
    var idComprado: Int? = 0
    var idLista: Int? = 0
    var nombre: String? = null
    var cantidad: String? = null

    constructor(
        idComprado: Int?,
        idLista: Int?,
        nombre: String?,
        cantidad: String?,
    ) {
        this.idComprado = idComprado
        this.idLista = idLista
        this.nombre = nombre
        this.cantidad = cantidad
    }
}