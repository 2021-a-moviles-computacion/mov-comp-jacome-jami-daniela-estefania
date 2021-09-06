package com.example.firebaseuno.dto

class FirestoreProductoDto(
    var uid:String = "",
    val nombre: String? = null,
    val precio: Double? = null ) {

    override fun toString(): String {
        if(nombre == null && precio == null)
            return "Producto no seleccionado"
        else
            return "${nombre}"
    }


}